package mrlee.kt_mall.product.market.makeshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrlee.kt_mall.member.entity.MemberMarket;
import mrlee.kt_mall.product.market.MarketLink;
import mrlee.kt_mall.product.market.util.MarketURLUtil;
import mrlee.kt_mall.product.market.makeshop.dto.request.RequestLinkForm;
import mrlee.kt_mall.product.market.makeshop.dto.response.ResponseLinkForm;
import mrlee.kt_mall.product.market.dto.RequestMarkerLinkForm;
import mrlee.kt_mall.common.util.HttpUtil;
import mrlee.kt_mall.common.util.MyUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MakeShopLinker implements MarketLink {

    public static final MakeShopLinker MAKE_SHOP_LINKER = new MakeShopLinker();
    private final ObjectMapper objectMapper = MyUtil.getCustomObjectMapper();

    public static MakeShopLinker getInstance() {
        return MAKE_SHOP_LINKER;
    }

    @Override
    public String createProduct(RequestMarkerLinkForm dto) throws IOException {
        String jsonBody = MyUtil.convertToJson(new RequestLinkForm(dto.getProductMarket()));
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, createHeader(dto.getMemberMarket()));
        String responseBody = HttpUtil.post(MarketURLUtil.getMakeShopCreateProduct(dto.getMemberMarket()), httpEntity);
        ResponseLinkForm responseLinkForm = getResponseLinkForm(responseBody);
        ReturnCode.validate(responseLinkForm.getReturnCode());
        return responseLinkForm.getDatas().getUid();
    }

    @Override
    public void updateProduct() throws IOException {
    }

    private HttpHeaders createHeader(MemberMarket memberMarket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Shopkey", memberMarket.getApiKey1());
        headers.add("Licensekey", memberMarket.getApiKey2());
        return headers;
    }

    private ResponseLinkForm getResponseLinkForm(String responseBody) throws IOException {
        String fileName = "200".equals(responseBody) ? "makeshop-success.json" : "makeshop-fail.json";
        byte[] bytes = MyUtil.getMarketFileObject(fileName);
        return objectMapper.reader().readValue(bytes, ResponseLinkForm.class);
    }
}
