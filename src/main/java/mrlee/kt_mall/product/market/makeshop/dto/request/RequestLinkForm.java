package mrlee.kt_mall.product.market.makeshop.dto.request;

import lombok.Getter;
import mrlee.kt_mall.product.entity.ProductMarket;

@Getter
public class RequestLinkForm {
    private final String productName;
    private final int sellPrice;

    public RequestLinkForm(ProductMarket productMarket) {
        this.productName = productMarket.getName();
        this.sellPrice = productMarket.getPrice();
    }
}
