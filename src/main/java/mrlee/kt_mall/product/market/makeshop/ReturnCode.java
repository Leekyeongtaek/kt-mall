package mrlee.kt_mall.product.market.makeshop;

import lombok.Getter;

@Getter
public enum ReturnCode {
    SUCCESS("0000", "성공"),
    SHOP_EXPIRATION("9998", "상점 이용 기간이 만료되었습니다."),
    NOT_FOUND_PRODUCT("9071", "상품번호가 없습니다."),
    NO_DATA("9201", "조회할 주문번호가 없습니다."),
    NO_API_KEY("9001", "상점키가 없습니다.");

    private final String code;
    private final String message;

    ReturnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static void validate(String code) {
        if (SUCCESS.getCode().equals(code)) {
            return;
        }
        for (ReturnCode returnCode : ReturnCode.values()) {
            if (returnCode.getCode().equals(code)) {
                throw new RuntimeException(returnCode.getMessage());
            }
        }
        throw new RuntimeException("해당하는 응답코드를 찾을 수 없습니다. 코드 번호 = " + code);
    }
}
