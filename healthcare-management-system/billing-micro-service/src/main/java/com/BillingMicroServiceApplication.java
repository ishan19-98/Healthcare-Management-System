package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "com.entity")
@EnableDiscoveryClient
@EnableKafka
public class BillingMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingMicroServiceApplication.class, args);
		System.err.println("Billing Service up at port 8484");
	}

}
