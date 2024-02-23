package com.project.EmployeeinfoBackendHRApplication;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.EmployeeinfoBackendHRApplication.service.CalculationsService;

@Component
public class Runner implements ApplicationRunner {

    private CalculationsService calculationsService;

    @Autowired
    public Runner(CalculationsService calculationsService) {
        this.calculationsService = calculationsService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter the month (e.g., JAN): ");
        String month = scanner.next();

//        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        
        calculationsService.calculateAndSaveDataForAllEmployees(null, 0);
        System.out.println("PDFs Generated");
    }
}








//package com.project.EmployeeinfoBackendHRApplication;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//
//@SpringBootApplication
//@ComponentScan("com.project.EmployeeinfoBackendHRApplication")
//public class EmployeeinfoBackendHrApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(EmployeeinfoBackendHrApplication.class, args);
//	}
//
//}

