package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;

@Service
public class PwdMailSender{
	@Autowired
	EmailService emailservice;
	
	private int username;
	private String userpassword;
	private String name;


public ResponseEntity<?> PwdMailsender( Employee employee){
		username=employee.getRnNo();
		System.out.println(username);
		userpassword=employee.getEmployeepassword();
		name=employee.getName();
		String message="Hello "+name+",this is your temporary password: "+userpassword+"";
		emailservice.sendMail(employee.getEmail(),message,"Login Details for your SMS account");
        return ResponseEntity.ok("Email sent successfully");
}
}