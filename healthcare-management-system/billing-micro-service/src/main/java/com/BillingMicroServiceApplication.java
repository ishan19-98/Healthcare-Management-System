package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.repository")
@EnableFeignClients
@EnableDiscoveryClient
public class BillingMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingMicroServiceApplication.class, args);
		System.err.println("Patient Service Up at port 8484");
	}

}
