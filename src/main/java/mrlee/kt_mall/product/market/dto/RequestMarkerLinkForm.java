package mrlee.kt_mall.product.market.dto;

import lombok.Getter;
import mrlee.kt_mall.member.entity.Member;
import mrlee.kt_mall.member.entity.MemberMarket;
import mrlee.kt_mall.product.entity.ProductMarket;

@Getter
public class RequestMarkerLinkForm {
    private final ProductMarket productMarket;
    private final MemberMarket memberMarket;

    public RequestMarkerLinkForm(ProductMarket productMarket, Member member) {
        this.productMarket = productMarket;
        this.memberMarket = member.getMemberMarket(productMarket.getMarketType());
    }
}
