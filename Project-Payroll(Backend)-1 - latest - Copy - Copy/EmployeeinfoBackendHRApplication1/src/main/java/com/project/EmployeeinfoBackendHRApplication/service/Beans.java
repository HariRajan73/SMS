package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Beans {

@Bean
public com.project.EmployeeinfoBackendHRApplication.controller.MailSender mailsender() {
	return new com.project.EmployeeinfoBackendHRApplication.controller.MailSender();
}

//@Bean
//public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//}
//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	http.cors().disable();
//	http.authorizeHttpRequests(req -> req.anyRequest().permitAll());
//	return http.build();
//}

}
