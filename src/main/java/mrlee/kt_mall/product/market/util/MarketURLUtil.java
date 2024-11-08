package mrlee.kt_mall.product.market.util;

import mrlee.kt_mall.member.entity.MemberMarket;

public class MarketURLUtil {

    public static String getMakeShopCreateProduct(MemberMarket memberMarket) {
        final String url = "http://{mallId}/list/open_api_process.html?ode=save&type=product&process=create";
        return url.replace("{mallId}", memberMarket.getMarketName());
    }

    public static String getCafe24CreateProduct(MemberMarket memberMarket) {
        final String url = "https://{mall_id}.cafe24api.com/api/v2/admin/products";
        return url.replace("{mall_id}", memberMarket.getMarketName());
    }

    public static String getCafer24AccessToken(MemberMarket memberMarket) {
        final String url = "https://{mallid}.cafe24api.com/api/v2/oauth/token";
        return url.replace("{mallid}", memberMarket.getMarketName());
    }
}
