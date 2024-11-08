package mrlee.kt_mall.product.market;

import lombok.RequiredArgsConstructor;
import mrlee.kt_mall.member.entity.Member;
import mrlee.kt_mall.product.entity.MARKET_TYPE;
import mrlee.kt_mall.product.entity.ProductMarket;
import mrlee.kt_mall.product.entity.ProductMarketLinkHistory;
import mrlee.kt_mall.product.market.cafe24.Cafe24Linker;
import mrlee.kt_mall.product.market.makeshop.MakeShopLinker;
import mrlee.kt_mall.product.repository.ProductMarketLinkHistoryRepository;
import org.springframework.stereotype.Component;

import static mrlee.kt_mall.product.entity.StatusCode.FAIL;
import static mrlee.kt_mall.product.entity.StatusCode.SUCCESS;

/**
 * MarketLinker 역할: MarketLink 구현 클래스를 통해 오픈 마켓에 상품 등록, 수정 요청을 하고 응답 결과를 기록한다.
 */
@RequiredArgsConstructor
@Component
public class MarketLinker {

    private final ProductMarketLinkHistoryRepository productMarketLinkHistoryRepository;

    private MarketLink getProductMarketLink(MARKET_TYPE marketType) {
        return switch (marketType) {
            case CAFE24 -> Cafe24Linker.getInstance();
            case MAKE_SHOP -> MakeShopLinker.getInstance();
            default -> throw new IllegalArgumentException("현재 지원하지 않는 마켓입니다.");
        };
    }

    public void linkMarketProduct(Member member, ProductMarket productMarket) {
        MarketLink marketLink = getProductMarketLink(productMarket.getMarketType());
        try {
            String productCode = marketLink.createProduct(member, productMarket);
            productMarket.linkComplete(productCode);
            productMarketLinkHistoryRepository.save(new ProductMarketLinkHistory(productMarket.getId(), SUCCESS, "성공"));
        } catch (Exception e) {
            linkExceptionHandler(e, productMarket);
        }
    }

    private void linkExceptionHandler(Exception e, ProductMarket productMarket) {
        String message;
        if (e instanceof RuntimeException) {
            message = e.getMessage();
        } else {
            message = "마켓 연동 처리중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
        }
        ProductMarketLinkHistory productMarketLinkHistory = new ProductMarketLinkHistory(productMarket.getId(), FAIL, message);
        productMarketLinkHistoryRepository.save(productMarketLinkHistory);
        productMarket.linkFail();
    }
}
