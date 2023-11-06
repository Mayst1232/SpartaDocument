package com.sparta.spartadoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SpartaDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpartaDocApplication.class, args);
	}

}
