package com.brquickpoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
@EnableSwagger
public class BrquickpollApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrquickpollApplication.class, args);
	}
}
