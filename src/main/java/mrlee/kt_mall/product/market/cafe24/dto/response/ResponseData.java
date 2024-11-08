package mrlee.kt_mall.product.market.cafe24.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private int shopNo;
    private int productNo;
    private List<Category> category;
    private String productCode;
    private String customProductCode;
    private String productName;
    private String engProductName;
    private String internalProductName;
    private String modelName;
    private double priceExcludingTax;
    private double price;
    private double retailPrice;
    private double supplyPrice;
    private char display;
    private char selling;
    private char productCondition;
    private int productUsedMonth;
    private String priceContent;
    private char buyLimitByProduct;
    private char buyLimitType;
    private List<Integer> buyGroupList;
    private List<String> buyMemberIdList;
    private char repurchaseRestriction;
    private char singlePurchaseRestriction;
    private String buyUnitType;
    private int buyUnit;
    private String orderQuantityLimitType;
    private int minimumQuantity;
    private int maximumQuantity;
    private char pointsByProduct;
    private char pointsSettingByPayment;
    private List<PointsAmount> pointsAmount;
    private char exceptMemberPoints;
    private char adultCertification;
    private String description;
    private String mobileDescription;
    private String translatedDescription;
    private String paymentInfo;
    private String shippingInfo;
    private String exchangeInfo;
    private String serviceInfo;
    private String simpleDescription;
    private String summaryDescription;
    private String detailImage;
    private char hasOption;
    private String soldoutMessage;
    private char useNaverpay;
    private char naverpayType;
    private char useKakaopay;
    private String manufacturerCode;
    private String supplierCode;
    private String brandCode;
    private String trendCode;
    private double productWeight;
    private ExpirationDate expirationDate;
    private List<String> icon;
    private String hscode;
    private CountryHscode countryHscode;
    private char shippingCalculation;
    private char shippingFeeByProduct;
    private String shippingMethod;
    private ShippingPeriod shippingPeriod;
    private char shippingScope;
    private String shippingArea;
    private char shippingFeeType;
    private List<ShippingRates> shippingRates;
    private char prepaidShippingFee;
    private String clearanceCategoryCode;
    private char productShippingType;
    private char imageUploadType;
    private List<RelationalProduct> relationalProduct;
    private String productMaterial;
    private String englishProductMaterial;
    private String clothFabric;
    private String classificationCode;
    private double additionalPrice;
    private double marginRate;
    private char taxCalculation;
    private char taxType;
    private int taxRate;
    private char originClassification;
    private int originPlaceNo;
    private String madeInCode;
    private String createdDate;
    private List<AdditionalImage> additionalImage;
    private char exposureLimitType;
    private List<Integer> exposureGroupList;

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class Category {
        private int categoryNo;
        private String recommend;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class AdditionalImage {
        private String big;
        private String medium;
        private String small;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class CountryHscode {
        private String JPN;
        private String CHN;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class PointsAmount {
        private String paymentMethod;
        private String pointsRate;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class ProductVolume {
        private char useProductVolume;
        private String productWidth;
        private String productHeight;
        private String productLength;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class ExpirationDate {
        private String startDate;
        private String endDate;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class RelationalProduct {
        private int productNo;
        private char interrelated;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class ShippingPeriod {
        private int minimum;
        private int maximum;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class ShippingRates {
        private double shippingRatesMin;
        private double shippingRatesMax;
        private double shippingFee;
    }
}
