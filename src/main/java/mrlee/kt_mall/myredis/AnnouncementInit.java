package mrlee.kt_mall.myredis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AnnouncementInit {

    private final AnnounceRepository announceRepository;

    @PostConstruct
    public void init() {
        announceRepository.saveAll(setAnnouncement());
    }

    private List<Announcement> setAnnouncement() {
        Announcement announcement1 = new Announcement("[안내] 해외결제 사칭 문자 주의", "안녕하세요. G마켓입니다.\n" +
                "\n" +
                " \n" +
                "\n" +
                "G마켓 해외직구를 사칭하는 피싱 문자가 최근 다시 신고되고 있어 주의 안내드립니다.\n" +
                "\n" +
                "아래와 같이 G마켓 해외직구 승인결제 피싱 문자 또는 발신번호 006, 002 등으로 시작하는 피싱 문자를 수신하신 고객님께서는 통화 또는 문자 내 기재된 번호/링크 등을 클릭하지 않도록 주의하여 주시기 바랍니다.");
        Announcement announcement2 = new Announcement("[안내] 개인정보처리방침 개정 안내", "안녕하세요. G마켓입니다.\n" +
                "\n" +
                "G마켓의 개인정보처리방침 변경에 대하여 알려 드립니다.\n" +
                "개인정보의 개인정보 보호책임자 등을 현행화하여 당사의 개인정보처리방침이 개정될 예정입니다.");
        Announcement announcement3 = new Announcement("[안내] 우리다모아카드 서비스 변경 안내", "안녕하세요, G마켓입니다.\n" +
                "우리다모아카드 스마일포인트 적립 서비스가 아래와 같이 변경되오니 이용에 참고하시기 바랍니다.");
        Announcement announcement4 = new Announcement("[안내] 빅스마일데이 라이브방송 이벤트 안내\n", "안녕하세요 G마켓입니다.\n" +
                "\n" +
                "11/6(월)~11/19(일) 빅스마일데이 기간 중 진행되는 라이브방송 사전알림신청 & 구매인증 이벤트 응모처 안내 드립니다.\n" +
                "\n" +
                "아래 구글폼 링크에 접속하시면 이벤트에 참여하실 수 있습니다.");
        Announcement announcement5 = new Announcement("[안내] 추석 연휴 고객센터 휴무 및 배송안내", "안녕하세요. G마켓입니다.\n" +
                "\n" +
                "G마켓을 사랑해 주시는 모든 고객께 추석 연휴를 맞이하여 감사의 마음을 전하며,\n" +
                "\n" +
                "추석 연휴 기간 고객센터 휴무 일정 및 배송/반품/교환 관련 안내드립니다.");
        return List.of(announcement1, announcement2, announcement3, announcement4, announcement5);
    }
}
