package mrlee.kt_mall.product.market.cafe24.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccessToken {
    private String accessToken;
    private String expiresAt;
    private String refreshToken;
    private String refreshTokenExpiresAt;
    private String clientId;
    private String mallId;
    private String userId;
    private String issuedAt;
}
