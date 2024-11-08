package mrlee.kt_mall.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.audit.AuditingDateTime;
import org.apache.logging.log4j.util.Strings;

import static mrlee.kt_mall.product.entity.ProductMarket.LINK_STATUS.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCT_MARKET")
@Entity
public class ProductMarket extends AuditingDateTime {

    enum LINK_STATUS {WAITING_SELLING, STOP_SELLING, WAITING_UPDATE, LINK_FAIL, LINK_COMPETE}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_MARKET_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "MARKET_PRODUCT_CODE", length = 100)
    private String marketProductCode;

    @Column(name = "NAME", length = 150, nullable = false)
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "MARKET_TYPE", length = 30)
    private MARKET_TYPE marketType;

    @Enumerated(EnumType.STRING)
    @Column(name = "LINK_STATUS", length = 45)
    private LINK_STATUS linkStatus;

    public ProductMarket(String name, int price, MARKET_TYPE marketType) {
        this.name = name;
        this.price = price;
        this.marketType = marketType;
        this.linkStatus = WAITING_SELLING;
    }

    public void mappingProduct(Product product) {
        this.product = product;
    }

    public void isLinked() {
        if (!Strings.isBlank(marketProductCode)) {
            throw new IllegalArgumentException("이미 마켓에 연동한 상품입니다.");
        }
    }

    public void linkComplete(String marketProductCode) {
        this.marketProductCode = marketProductCode;
        this.linkStatus = LINK_COMPETE;
    }

    public void linkFail() {
        this.linkStatus = LINK_FAIL;
    }
}
