package org.mlooser.learn.spring.reactivestudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactiveStudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveStudentsApplication.class, args);
	}

}
