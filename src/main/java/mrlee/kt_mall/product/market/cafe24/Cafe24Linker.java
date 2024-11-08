package mrlee.kt_mall.product.market.cafe24;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.util.HttpUtil;
import mrlee.kt_mall.common.util.MyUtil;
import mrlee.kt_mall.member.entity.MemberMarket;
import mrlee.kt_mall.product.market.MarketLink;
import mrlee.kt_mall.product.market.cafe24.dto.request.RequestLinkForm;
import mrlee.kt_mall.product.market.cafe24.dto.response.AccessToken;
import mrlee.kt_mall.product.market.cafe24.dto.response.LinkResponseForm;
import mrlee.kt_mall.product.market.dto.RequestMarkerLinkForm;
import mrlee.kt_mall.product.market.util.MarketURLUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cafe24Linker implements MarketLink {

    public static final Cafe24Linker CAFE_24_LINKER = new Cafe24Linker();
    private final ObjectMapper objectMapper = MyUtil.getCustomObjectMapper();

    public static Cafe24Linker getInstance() {
        return CAFE_24_LINKER;
    }

    @Override
    public String createProduct(RequestMarkerLinkForm dto) throws IOException {
        String jsonBody = MyUtil.convertToJson(new RequestLinkForm(dto.getProductMarket()));
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, createHeader(dto.getMemberMarket()));
        String responseBody = HttpUtil.post(MarketURLUtil.getCafe24CreateProduct(dto.getMemberMarket()), entity);
        LinkResponseForm linkResponseForm = getLinkResponseForm(responseBody);
        responseCodeValidate(linkResponseForm.getReturnCode());
        return linkResponseForm.getProduct().getProductCode();
    }

    @Override
    public void updateProduct() throws IOException {
    }

    private void responseCodeValidate(String returnCode) {
        if (returnCode.startsWith("4")) {
            throw new RuntimeException("요청 데이터 또는 방식이 올바르지 않습니다.");
        } else if (returnCode.startsWith("5")) {
            throw new RuntimeException("카페24 서버에 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    private HttpHeaders createHeader(MemberMarket memberMarket) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Authorization", "Bearer " + getAccessToken(memberMarket));
        return header;
    }

    private AccessToken getAccessToken(MemberMarket memberMarket) throws IOException {
        final String authorizationValue = "Basic {base64_encode({client_id}:{client_secret})}"
                .replace("{client_id}", memberMarket.getClientId())
                .replace("{client_secret}", memberMarket.getApiKey1());

        final String mediaTypeValue = "grant_type=authorization_code&code={code}&redirect_uri={redirect_uri}"
                .replace("{code}", "exampleCode")
                .replace("{redirect_uri}", "example.com");

        RequestBody body = RequestBody.create(mediaTypeValue, okhttp3.MediaType.parse(""));

        Request request = new Request.Builder()
                .url(MarketURLUtil.getCafer24AccessToken(memberMarket))
                .addHeader("Authorization", authorizationValue)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        //Response response = client.newCall(request).execute();
        return getAccessToken();
    }

    private AccessToken getAccessToken() throws IOException {
        byte[] bytes = MyUtil.getMarketFileObject("cafe24-accesstoken.json");
        return objectMapper.reader().readValue(bytes, AccessToken.class);
    }

    private LinkResponseForm getLinkResponseForm(String responseBody) throws IOException {
        String fileName = "200".equals(responseBody) ? "cafe24-success.json" : "cafe24-fail.json";
        byte[] bytes = MyUtil.getMarketFileObject(fileName);
        return objectMapper.reader().readValue(bytes, LinkResponseForm.class);
    }
}
