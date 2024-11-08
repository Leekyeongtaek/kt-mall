package mrlee.kt_mall.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.audit.AuditingDateTime;
import mrlee.kt_mall.product.entity.MARKET_TYPE;

import java.util.List;
import java.util.NoSuchElementException;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member extends AuditingDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member", fetch = LAZY)
    private List<MemberMarket> memberMarkets;

    public MemberMarket getMemberMarket(MARKET_TYPE marketType) {
        return memberMarkets.stream()
                .filter(e -> e.getMarketType().equals(marketType))
                .findAny().orElseThrow(NoSuchElementException::new);
    }
}
