package org.tourGo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing사용(엔티티공통 속성화)
@SpringBootApplication
public class TourGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourGoApplication.class, args);
	}

}
