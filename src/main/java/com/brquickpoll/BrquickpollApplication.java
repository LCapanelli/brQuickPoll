package com.brquickpoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfig.class })
public class BrquickpollApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrquickpollApplication.class, args);
	}
}
