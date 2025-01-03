package mrlee.kt_mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class KtMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtMallApplication.class, args);
	}

	@Bean
	public InMemoryHttpExchangeRepository httpExchangeRepository() {
		return new InMemoryHttpExchangeRepository();
	}

}
