package mrlee.kt_mall.product.entity;

import jakarta.persistence.*;
import mrlee.kt_mall.common.audit.AuditingDateTime;

//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "PRODUCT_MARKET_CONTENTS")
//@Entity
public class ProductMarketContents extends AuditingDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_MARKET_CONTENTS_ID")
    private Long id;

    @Column(name = "SUPPLY_PRICE")
    private int supplyPrice;

    @Column(name = "PRODUCTION", length = 45)
    private String production;

    @Column(name = "ORIGIN", length = 45)
    private String origin;

    @Column(name = "BRAND_NAME", length = 45)
    private String brand_name;

    @Column(name = "DISPLAY_YN", length = 10, nullable = false)
    private String displayYn;

    @Column(name = "SELLING_YN", length = 10, nullable = false)
    private String sellingYn;

    @Column(name = "UNIQUENESS", length = 45)
    private String uniqueness;

    @Column(name = "PRODUCT_CONDITION", length = 45)
    private String productCondition;

    @Column(name = "TAX_TYPE", length = 45)
    private String taxType;

    @Column(name = "SUPPLY_PRODUCT_NAME", length = 45)
    private String supplyProductName;

    @Column(name = "SOLD_OUT_MESSAGE", length = 45)
    private String soldOutMessage;

    @Column(name = "MANUFACTURE_CODE", length = 45)
    private String manufacturerCode;
}
