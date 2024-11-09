# 이경택 포트폴리오: KT-MALL
## 주제
- 온라인 마켓에 상품을 연동하는 프로그램

## 참고사항
- 온라인 마켓의 API를 사용하려면 마켓별로 발급된 키가 필요하기 때문에, http 요청은 객체만 만들고 실제 호출하지 않도록 코드를 작성했습니다.
- 마켓별 성공/실패 응답 데이터는 resources/response/cafe24.json, makeshop.json 파일 데이터로 대체하였습니다.

### 프로젝트 상세
- IDE : IntelliJ
- Java : 17
- SpringBoot : 3.3.5
- MySql : 8.0.32

### 테이블 ERD
![이경택의 kt-mall](https://github.com/user-attachments/assets/b6f52473-d2e6-48d8-8cb1-c283f641e609)

### 상품 마켓 연동 객체 관계도
<img width="589" alt="스크린샷 2024-11-08 오후 9 31 32" src="https://github.com/user-attachments/assets/812f4a78-8d22-4203-af69-cb46b8e0fbf3">

### 마켓 상품 연동 과정(메이크샵) 예시
- 서비스 계층: ProductService
```
public void linkProductMarket(SaveProductMarketLink saveProductMarketLink) {
    1. 회원 정보 조회
    Member member = memberRepository.findByIdJoinFetchMemberMarket(saveProductMarketLink.getMemberId()).orElseThrow(IllegalArgumentException::new);
    2. 마켓 상품 목록 조회
    List<ProductMarket> productMarkets = productMarketRepository.findAllById(saveProductMarketLink.getProductMarketIds());
    3. 마켓 등록 여부 유효성 검사
    productMarkets.forEach(ProductMarket::isLinked);
    4. 반복문을 통해 연동 메서드 호출
    for (ProductMarket productMarket : productMarkets) {
        marketLinker.linkMarketProduct(member, productMarket);
    }
}
```
- 연동 관리 객체: MarketLinker
```
연동 구현 클래스 조회 메서드
private MarketLink getProductMarketLink(MARKET_TYPE marketType) {
    return switch (marketType) {
        case CAFE24 -> Cafe24Linker.getInstance();
        case MAKE_SHOP -> MakeShopLinker.getInstance();
        default -> throw new IllegalArgumentException("현재 지원하지 않는 마켓입니다.");
    };
}

public void linkMarketProduct(Member member, ProductMarket productMarket) {
    1. 마켓 연동 구현 클래스 조회
    MarketLink marketLink = getProductMarketLink(productMarket.getMarketType());
    try {
        2. 연동 등록 성공시 마켓 상품 번호 반환
        String productCode = marketLink.createProduct(member, productMarket);
        2-1. 마켓 상품의 연동 상태를 연동완료로 변경하고 발급 받은 상품 코드 입력
        productMarket.linkComplete(productCode);
        2-2. 연동 기록 테이블에 성공 내역 기록
        productMarketLinkHistoryRepository.save(new ProductMarketLinkHistory(productMarket.getId(), SUCCESS, "성공"));
    } catch (Exception e) {
        3. 연동 실패시 예외 핸들러 메소드 호출 
        linkExceptionHandler(e, productMarket);
    }
}

연동 중 예외 발생 처리 메서드
private void linkExceptionHandler(Exception e, ProductMarket productMarket) {
    String message;
    if (e instanceof RuntimeException) {
        message = e.getMessage();
    } else {
        message = "마켓 연동 처리중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
    }
    ProductMarketLinkHistory productMarketLinkHistory = new ProductMarketLinkHistory(productMarket.getId(), FAIL, message);
    3-1. 연동 기록 테이블에 실패 내역 기록
    productMarketLinkHistoryRepository.save(productMarketLinkHistory);
    3-2. 마켓 상품의 연동 상태를 연동실패로 변경
    productMarket.linkFail();
}
```
- 마켓 연동 인터페이스
```
public interface MarketLink {
    String createProduct(Member member, ProductMarket productMarket) throws IOException;
    void updateProduct() throws IOException;
}
```
- 메이크샵 구현 객체: MakeShopLinker
```
@Override
public String createProduct(Member member, ProductMarket productMarket) throws IOException {
    1. String json 데이터 변환
    String jsonBody = MyUtil.convertToJson(new RequestLinkForm(productMarket));
    2. 헤더 생성 메서드 호출 및 HTTP 엔티티 객체 생성
    HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, createHeader(member.getMemberMarket(productMarket.getMarketType())));
    3. Http 유틸을 통해 메이크샵 상품 등록 API 호출
    String responseBody = HttpUtil.post(MarketURLUtil.getMakeShopCreateProduct(member.getMemberMarket(productMarket.getMarketType())), httpEntity);
    3-1. 메이크샵 결과 파일을 객체 생성
    ResponseLinkForm responseLinkForm = getResponseLinkForm(responseBody);
    4. 응답 코드에 따른 성공/실패 유효성 검증
    ReturnCode.validate(responseLinkForm.getReturnCode());
    5. 마켓에서 발급 받은 상품 코드 반환
    return responseLinkForm.getDatas().getUid();
}

HTTP 헤더 생성 메서드
private HttpHeaders createHeader(MemberMarket memberMarket) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Shopkey", memberMarket.getApiKey1());
    headers.add("Licensekey", memberMarket.getApiKey2());
    return headers;
}

메이크샵 응답 파일 조회 메서드
private ResponseLinkForm getResponseLinkForm(String responseBody) throws IOException {
    String fileName = "200".equals(responseBody) ? "makeshop-success.json" : "makeshop-fail.json";
    byte[] bytes = MyUtil.getMarketFileObject(fileName);
    return objectMapper.reader().readValue(bytes, ResponseLinkForm.class);
}

메이크샵 응답 코드 열거 클래스
@Getter
public enum ReturnCode {
    SUCCESS("0000", "성공"),
    SHOP_EXPIRATION("9998", "상점 이용 기간이 만료되었습니다."),
    NOT_FOUND_PRODUCT("9071", "상품번호가 없습니다."),
    NO_DATA("9201", "조회할 주문번호가 없습니다."),
    NO_API_KEY("9001", "상점키가 없습니다.");

    private final String code;
    private final String message;

    ReturnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static void validate(String code) {
        if (SUCCESS.getCode().equals(code)) {
            return;
        }
        for (ReturnCode returnCode : ReturnCode.values()) {
            if (returnCode.getCode().equals(code)) {
                throw new RuntimeException(returnCode.getMessage());
            }
        }
        throw new RuntimeException("해당하는 응답코드를 찾을 수 없습니다. 코드 번호 = " + code);
    }
}
```
