package mrlee.kt_mall.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mrlee.kt_mall.common.audit.AuditingDateTime;
import mrlee.kt_mall.product.entity.MARKET_TYPE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER_MARKET")
@Entity
public class MemberMarket extends AuditingDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_MARKET_ID")
    private Long id;

    @Column(name = "API_KEY1")
    private String apiKey1;

    @Column(name = "API_KEY2")
    private String apiKey2;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "MARKET_NAME")
    private String marketName;

    @Column(name = "MARKET_TYPE")
    @Enumerated(EnumType.STRING)
    private MARKET_TYPE marketType;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
