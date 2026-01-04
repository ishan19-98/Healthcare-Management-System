package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
@EnableDiscoveryClient
@EnableScheduling
public class DoctorMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorMicroServiceApplication.class, args);
		System.err.println("Doctor Service Up at port 8282");
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate()
	{
		RestTemplate template = new RestTemplate();
		return template;
	}
	

}
