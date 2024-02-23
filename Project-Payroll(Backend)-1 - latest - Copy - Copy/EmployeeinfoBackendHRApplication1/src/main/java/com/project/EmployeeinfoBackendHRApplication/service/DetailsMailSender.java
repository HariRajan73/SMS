package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DetailsMailSender implements EmailService {

    @Autowired
    private JavaMailSender mail;

    public void sendMail(String toEmail, String body, String subject) {

    	if (toEmail == null || toEmail.isEmpty() || body == null || body.isEmpty() || subject == null || subject.isEmpty()) {

            System.out.println("Invalid parameters for sending email.");
            return;
        }

        if (!isValidEmail(toEmail)) {
            System.out.println("Invalid email address: " + toEmail);
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hari13rajan@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        
        try {
            mail.send(message);
            System.out.println("MAIL SENT SUCCESSFULLY....");
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailPattern);
    }
}
