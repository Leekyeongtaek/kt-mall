package mrlee.kt_mall.product.market.makeshop.dto.response;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResponseLinkForm {
    private String returnCode;
    private ResponseData datas;
}
