package com.thiagodev.app.netflixeurekanamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NetflixEurekaNamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServiceApplication.class, args);
	}
}
