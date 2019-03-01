package com.skyforce.SkyForceWebService.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.skyforce.SkyForceWebService")
@EntityScan("com.skyforce.SkyForceWebService.model")
@EnableJpaRepositories(basePackages = {"com.skyforce.SkyForceWebService.repo"})

public class SkyForceWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyForceWebServiceApplication.class, args);
	}

}
