package mrlee.kt_mall;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import mrlee.kt_mall.product.market.cafe24.dto.response.LinkResponseForm;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MyTest {

    @Test
    void a() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        String preFilePath = "/Users/myhome/Desktop/programming/project/kt-mall/src/main/resources/response/";
        String filePath = "cafe24-success.json";
        FileInputStream fis = new FileInputStream(preFilePath + filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        LinkResponseForm linkResponseForm = objectMapper.reader().readValue(bis.readAllBytes(), LinkResponseForm.class);
        System.out.println("cafe24Response = " + linkResponseForm);
    }
}
