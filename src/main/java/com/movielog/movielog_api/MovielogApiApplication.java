package com.movielog.movielog_api;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovielogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovielogApiApplication.class, args);
	}

}
