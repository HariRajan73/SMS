package com.project.EmployeeinfoBackendHRApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.EmployeeinfoBackendHRApplication")
public class EmployeeinfoBackendHrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeinfoBackendHrApplication.class, args);
	}

}
