package mrlee.kt_mall.product.dto;

import jakarta.validation.constraints.NotBlank;
import mrlee.kt_mall.product.entity.MARKET_TYPE;
import mrlee.kt_mall.product.entity.ProductMarket;
import org.hibernate.validator.constraints.Range;

public class SaveProductMarket {
    @NotBlank(message = "마켓 상품명은 필수입니다.")
    private final String name;
    @Range(min = 1, max = 100000, message = "마켓 상품 가격은 1 ~ 100,000 사이로 입력 가능합니다.")
    private final int price;
    private final MARKET_TYPE marketType;

    public SaveProductMarket(String name, int price, MARKET_TYPE marketType) {
        this.name = name;
        this.price = price;
        this.marketType = marketType;
    }

    public ProductMarket toProductMarket() {
        return new ProductMarket(name, price, marketType);
    }
}
