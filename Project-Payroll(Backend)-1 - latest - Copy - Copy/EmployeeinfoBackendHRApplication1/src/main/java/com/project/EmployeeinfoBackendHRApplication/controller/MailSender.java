package com.project.EmployeeinfoBackendHRApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;
import com.project.EmployeeinfoBackendHRApplication.service.EmailService;

@Service
public class MailSender{
	@Autowired
	EmailService emailservice;
	
	private int username;
	private String userpassword;
	private String name;


public ResponseEntity<?> Mailsender( Employee employee){
		username=employee.getRnNo();
		System.out.println(username);
		userpassword=employee.getEmployeepassword();
		name=employee.getName();
		String message="Hello "+name+" you can login by using the below given username : "+username+"  and the userpassword : "+userpassword+ ".Click http://localhost:4200 to login.";
		emailservice.sendMail(employee.getEmail(),message,"Login Details for your SMS account");
        return ResponseEntity.ok("Email sent successfully");
		
	
}
	
	



}
