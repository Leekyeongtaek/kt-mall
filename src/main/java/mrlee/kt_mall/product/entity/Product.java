package mrlee.kt_mall.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.audit.AuditingDateTime;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCT")
@Entity
public class Product extends AuditingDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", length = 150, nullable = false)
    private String name;
    @Column(name = "PRICE")
    private int price;

    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = LAZY)
    private List<ProductMarket> productMarkets = new ArrayList<>();

    public Product(String name, int price, Long memberId) {
        this.name = name;
        this.price = price;
        this.memberId = memberId;
    }

    public void mappingProductMarket(List<ProductMarket> productMarkets) {
        for (ProductMarket productMarket : productMarkets) {
            productMarket.mappingProduct(this);
            this.productMarkets.add(productMarket);
        }
    }
}
