package mrlee.kt_mall.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    private final static RestTemplate restTemplate = new RestTemplate();
    private static int cnt = 0;

    private static String getReturnCode() {
        cnt++;
        if (cnt % 2 != 0) {
            return "200";
        } else {
            return "400";
        }
    }

    public static String post(String url, HttpEntity<String> httpEntity) {
        //restTemplate.postForObject(url, httpEntity, String.class);
        return getReturnCode();
    }

    public static String patch(String url, HttpEntity<String> httpEntity) {
        //restTemplate.patchForObject(url, httpEntity, String.class);
        return getReturnCode();
    }

    public static String get(String url) {
        //restTemplate.getForObject(url, String.class);
        return getReturnCode();
    }
}
