package mrlee.kt_mall.member.repository;

import mrlee.kt_mall.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m JOIN FETCH m.memberMarkets WHERE m.id = :memberId")
    Optional<Member> findByIdJoinFetchMemberMarket(@Param("memberId") Long memberId);
}
