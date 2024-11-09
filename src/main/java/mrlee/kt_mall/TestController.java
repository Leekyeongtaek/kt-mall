package mrlee.kt_mall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    DataSource dataSource;
    private List<String> list = new ArrayList<>();


    @GetMapping("/call")
    public ResponseEntity<Void> call() {
        log.info("info call");
        log.debug("debug call");
        log.error("error call");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cpu")
    public String cpu() {
        log.info("cpu");
        long value = 0;
        for (long i = 0; i < 100000000000L; i++) {
            value++;
        }
        return "ok value=" + value;
    }

    @GetMapping("/jvm")
    public String jvm() {
        log.info("jvm");
        for (int i = 0; i < 10000000; i++) {
            list.add("hello jvm!" + i);
        }
        return "ok";
    }

    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();
        log.info("connection info={}", conn);
        //conn.close(); //커넥션을 닫지 않는다.
        return "ok";
    }
}
