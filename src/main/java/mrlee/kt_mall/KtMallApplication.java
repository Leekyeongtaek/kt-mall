package mrlee.kt_mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KtMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtMallApplication.class, args);
	}

}
