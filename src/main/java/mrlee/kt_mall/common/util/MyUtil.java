package mrlee.kt_mall.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class MyUtil {

    private static final String preFilePath = "/Users/myhome/Desktop/programming/project/kt-mall/src/main/resources/response/";

    public static ObjectMapper getCustomObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    public static <T> String convertToJson(T t) {
        ObjectMapper objectMapper = getCustomObjectMapper();
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.info("예외 클래스 정보 = {}", t.getClass().getName());
            throw new RuntimeException("Json 데이터 변환 중 예외가 발생했습니다.", e);
        }
    }

    public static byte[] getMarketFileObject(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(preFilePath + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            return bis.readAllBytes();
        } catch (FileNotFoundException e) {
            log.info("예외 발생 파일명 = {}", fileName);
            throw new RuntimeException("해당하는 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            log.info("예외 발생 파일명 = {}", fileName);
            throw new RuntimeException("파일 입출력중 예외가 발생했습니다.");
        }
    }


}
