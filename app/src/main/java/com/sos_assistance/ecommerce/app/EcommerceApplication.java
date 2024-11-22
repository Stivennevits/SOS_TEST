package com.sos_assistance.ecommerce.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sos_assistance.ecommerce")
@EnableJpaRepositories(basePackages = "com.sos_assistance.ecommerce.domain.repository")
@EntityScan(basePackages = "com.sos_assistance.ecommerce.domain.model")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
