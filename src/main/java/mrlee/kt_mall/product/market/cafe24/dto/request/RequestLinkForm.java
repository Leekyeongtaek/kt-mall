package mrlee.kt_mall.product.market.cafe24.dto.request;

import lombok.Getter;
import mrlee.kt_mall.product.entity.ProductMarket;

@Getter
public class RequestLinkForm {
    private final String productName;
    private final int price;

    public RequestLinkForm(ProductMarket productMarket) {
        this.productName = productMarket.getName();
        this.price = productMarket.getPrice();
    }
}
