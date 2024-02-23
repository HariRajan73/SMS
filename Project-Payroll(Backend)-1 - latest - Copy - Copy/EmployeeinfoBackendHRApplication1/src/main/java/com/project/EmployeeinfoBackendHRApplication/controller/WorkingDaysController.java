package com.project.EmployeeinfoBackendHRApplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.entity.WorkingDays;
import com.project.EmployeeinfoBackendHRApplication.service.WorkingDaysService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WorkingDaysController {

    private final WorkingDaysService workingDaysService;

    @Autowired
    public WorkingDaysController(WorkingDaysService workingDaysService) {
        this.workingDaysService = workingDaysService;
    }

    @PostMapping("/working-days")
    public ResponseEntity<WorkingDays> createWorkingDays(@RequestBody WorkingDays workingDays) {
        WorkingDays createdWorkingDays = workingDaysService.createWorkingDays(workingDays);
        return new ResponseEntity<>(createdWorkingDays, HttpStatus.CREATED);
    }
    
    
}
