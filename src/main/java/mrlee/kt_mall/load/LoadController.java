package mrlee.kt_mall.load;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/load")
@RestController
public class LoadController {

    @GetMapping("/")
    public void plusNumberTo1000000() {
        long sum = 0;
        for (long i = 0; i < 2000000000; i++) {
            sum += i;
        }
        log.info("계산 결과: {}, 스레드 이름: {}", sum, Thread.currentThread().getName());
    }
}
