package mrlee.kt_mall.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveProductMarketLink {
    private final Long memberId;
    List<Long> productMarketIds;

    public SaveProductMarketLink(Long memberId, List<Long> productMarketIds) {
        this.memberId = memberId;
        this.productMarketIds = productMarketIds;
    }
}
