package com.qrata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QrataApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrataApplication.class, args);
	}
}
