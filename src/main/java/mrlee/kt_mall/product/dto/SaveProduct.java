package mrlee.kt_mall.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mrlee.kt_mall.product.entity.Product;
import org.hibernate.validator.constraints.Range;

public class SaveProduct {
    @NotBlank(message = "마스터 상품명은 필수입니다.")
    private final String name;
    @Range(min = 1, max = 100000, message = "마스터 상품 가격은 1 ~ 100,000 원 사이로 입력 가능합니다.")
    private final int price;

    @NotNull(message = "회원 번호는 필수입니다.")
    private final Long memberId;

    public SaveProduct(String name, int price, Long memberId) {
        this.name = name;
        this.price = price;
        this.memberId = memberId;
    }

    public Product toProductEntity() {
        return new Product(name, price, memberId);
    }
}
