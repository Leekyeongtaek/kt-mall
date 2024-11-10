package mrlee.kt_mall.myredis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@RedisHash(value = "announcement")
public class Announcement {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Announcement(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Announcement update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }
}
