package toy.tiering.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import toy.tiering.api.oauth.properties.AppProperties;
import toy.tiering.api.oauth.properties.CorsProperties;

@EnableConfigurationProperties({AppProperties.class, CorsProperties.class})
@EnableJpaRepositories
@SpringBootApplication
@EnableJpaAuditing
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
