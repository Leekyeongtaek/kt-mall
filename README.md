# KT-MALL 프로젝트, 제작: 이경택
## 주제
- 온라인 마켓에 상품을 연동하는 프로그램

## 참고사항
- 온라인 마켓의 API를 사용하려면 마켓별로 발급된 키가 필요하기 때문에, http 요청은 객체만 만들고 실제 호출하지 않도록 코드를 작성했습니다.
- 마켓별 성공/실패 응답 데이터는 resources/response 디렉토리에 파일 데이터로 대체하였습니다.

## 목차

### 프로젝트 상세
- IDE : IntelliJ
- Java : 17
- SpringBoot : 3.3.5
- MySql : 8.0.32

### 테이블 ERD
![이경택의 kt-mall](https://github.com/user-attachments/assets/b6f52473-d2e6-48d8-8cb1-c283f641e609)

### 상품 마켓 연동 객체 관계도
![서비스 호출 흐름도](https://github.com/user-attachments/assets/7b47a6e9-078c-4187-8a44-e72b996a1af9)

### 핵심 코드
- 서비스 계층: ProductService(멤버 정보와 연동할 마켓 상품 목록 조회 역할)
```
public void linkProductMarket(SaveProductMarketLink saveProductMarketLink) {
        //1. 멤버 정보 조회
        Member member = memberRepository.findByIdJoinFetchMemberMarket(saveProductMarketLink.getMemberId()).orElseThrow(IllegalArgumentException::new);
        //2. 마켓 상품 목록 조회
        List<ProductMarket> productMarkets = productMarketRepository.findAllById(saveProductMarketLink.getProductMarketIds());
        //3. 이미 연동한 상품인지 유효성 검사
        productMarkets.forEach(ProductMarket::isLinked);
        //4. 상품 등록 폼으로 변환
        List<RequestMarkerLinkForm> requestMarkerLinkForms = productMarkets.stream()
                .map(pm -> new RequestMarkerLinkForm(pm, member))
                .toList();
        //5. 연동 담당 객체 호출
        for (RequestMarkerL출inkForm marketLinkForm : requestMarkerLinkForms) {
            marketLinker.linkMarketProduct(marketLinkForm);
        }
    }
```
- 연동 담당 객체: MarketLinker(마켓 타입에 따른 구현 클래스를 반환하고 등록 메서드 호출 및 연동 결과를 기록하는 역할)
```
//마켓 타입에 따른 MarketLink 구현 객체 반환
private MarketLink getProductMarketLink(MARKET_TYPE marketType) {
        return switch (marketType) {
            case CAFE24 -> Cafe24Linker.getInstance();
            case MAKE_SHOP -> MakeShopLinker.getInstance();
            default -> throw new IllegalArgumentException("현재 지원하지 않는 마켓입니다.");
        };
}
//구현 객체의 상품 등록 메소드 호출
public void linkMarketProduct(RequestMarkerLinkForm form) {
        ProductMarket productMarket = form.getProductMarket();
        MarketLink marketLink = getProductMarketLink(productMarket.getMarketType());
        try {
            form.getProductMarket().linkComplete(marketLink.createProduct(form));
            //상품 등록 성공시 결과 기록
            productMarketLinkHistoryRepository.save(new ProductMarketLinkHistory(productMarket.getId(), SUCCESS, "성공"));
        } catch (Exception e) {
            //상품 등록 실패시 예외 처리 메서드 호출
            linkExceptionHandler(e, productMarket.getId());
        }
}
//연동 실패시 메시지와 함께 결과 기록
private void linkExceptionHan록dler(Exception e, Long productMarketId) {
        String message;
        if (e instanceof RuntimeException) {
            message = e.getMessage();
        } else {
            message = "마켓 연동 처리중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
        }
        ProductMarketLinkHistory productMarketLinkHistory = new ProductMarketLinkHistory(productMarketId, FAIL, message);
        productMarketLinkHistoryRepository.save(productMarketLinkHistory);
    }
```
