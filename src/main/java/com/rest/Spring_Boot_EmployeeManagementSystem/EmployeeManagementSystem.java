package com.rest.Spring_Boot_EmployeeManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rest.Spring_Boot_EmployeeManagementSystem.repository",
		"com.rest.Spring_Boot_EmployeeManagementSystem.service",
		"com.rest.Spring_Boot_EmployeeManagementSystem.controller"
})
@EntityScan("com.rest.Spring_Boot_EmployeeManagementSystem.model")
@EnableJpaRepositories("com.rest.Spring_Boot_EmployeeManagementSystem.repository")
public class EmployeeManagementSystem {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystem.class, args);
	}

}
