package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "com.entity")
@EnableDiscoveryClient
public class AppointmentMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentMicroServiceApplication.class, args);
		System.err.println("Appointment Service Up at port 8383");

	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate()
	{
		RestTemplate template = new RestTemplate();
		return template;
	}

}
