package com.enduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.deliveryboy.repository")
@EntityScan(basePackages = "com.deliveryboy.entity")
public class EnduserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnduserApplication.class, args);
	}

}
