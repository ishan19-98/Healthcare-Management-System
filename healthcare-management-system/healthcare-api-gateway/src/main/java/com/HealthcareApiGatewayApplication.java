package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthcareApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareApiGatewayApplication.class, args);
		System.err.println("Healthcare Api Gateway Service Up at port 8080");
	}

	@Bean
	public RouteLocator myCustomerLocator(RouteLocatorBuilder builder) {
		return builder.
				routes().route(r->r.path("/patient/**").and().method("GET","PUT","DELETE","POST").
						filters(f->f.addRequestParameter("msg", "Welcome")).uri("http://localhost:8181")).
				route(r->r.path("/doctor/**").and().method("GET","POST","PUT","DELETE").filters(f->f.addResponseHeader("res-key", "res-value")).
										uri("http://localhost:8282")).
				route(r->r.path("/appointment/**").and().method("GET","POST","PUT","DELETE").filters(f->f.addResponseHeader("res-key", "res-value")).
						uri("http://localhost:8383")).route(r->r.path("/bill/**").and().method("GET","POST","PUT","DELETE").filters(f->f.addResponseHeader("res-key", "res-value")).
								uri("http://localhost:8484")).
				build();
	}
}
