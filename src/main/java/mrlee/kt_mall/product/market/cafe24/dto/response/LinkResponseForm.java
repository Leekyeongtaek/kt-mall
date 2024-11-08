package mrlee.kt_mall.product.market.cafe24.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LinkResponseForm {
    private String returnCode;
    private ResponseData product;
}
