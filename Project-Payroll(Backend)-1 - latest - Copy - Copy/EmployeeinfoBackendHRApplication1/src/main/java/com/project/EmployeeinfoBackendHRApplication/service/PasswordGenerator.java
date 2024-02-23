package com.project.EmployeeinfoBackendHRApplication.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {
	private static final String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
public String generateTempPassword(int length) {
	SecureRandom random=new SecureRandom();
	StringBuilder password=new StringBuilder();
	for(int i=0;i<length;i++) {
		int randomIndex=random.nextInt(characters.length());
		char randomChar=characters.charAt(randomIndex);
		password.append(randomChar);
		
	}
	return password.toString();
}


}