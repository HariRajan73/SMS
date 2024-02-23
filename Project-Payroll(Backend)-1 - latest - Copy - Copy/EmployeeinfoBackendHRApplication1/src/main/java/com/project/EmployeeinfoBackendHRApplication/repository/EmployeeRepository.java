package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;

 @Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Integer>{
	    List<Employee> findByManagerRnNo(int rnNo);
	    
	    Optional<Employee> findByRnNo(int rnNo);
	    
	    Employee findByRnNoAndEmployeepassword(int rnNo, String employeepassword);   

}