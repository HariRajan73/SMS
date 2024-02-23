package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;
import com.project.EmployeeinfoBackendHRApplication.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    public Employee findByRnNoAndEmployeepassword(int rnNo, String employeepassword) {
    	
    	if (employeepassword == null) {
            throw new IllegalArgumentException("Employee password cannot be null");
        }

        if (rnNo <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        
        return employeeRepository.findByRnNoAndEmployeepassword(rnNo, employeepassword);
    }
}
