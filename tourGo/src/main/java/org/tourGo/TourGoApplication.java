package org.tourGo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class TourGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourGoApplication.class, args);
	}

	//메시지식 설정
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("messages.community", "messages.common"); //메시지파일 여기에 추가하기
		ms.setDefaultEncoding("UTF-8");
		
		return ms;
	}
}
