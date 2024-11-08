package mrlee.kt_mall.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.audit.AuditingDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCT_MARKET_LINK_HISTORY")
@Entity
public class ProductMarketLinkHistory extends AuditingDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_MARKET_LINK_HISTORY_ID")
    private Long id;

    @Column(name = "PRODUCT_MARKET_ID", nullable = false)
    private Long productMarketId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_CODE", length = 20)
    private StatusCode statusCode;

    @Column(name = "MESSAGE", length = 200, nullable = false)
    private String message;

    public ProductMarketLinkHistory(Long productMarketId, StatusCode statusCode, String message) {
        this.productMarketId = productMarketId;
        this.statusCode = statusCode;
        this.message = message;
    }
}
