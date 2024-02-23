package com.project.EmployeeinfoBackendHRApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.entity.Exemption;
import com.project.EmployeeinfoBackendHRApplication.service.ExemptionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ExemptionController {

    private final ExemptionService exemptionService;

    @Autowired
    public ExemptionController(ExemptionService exemptionService) {
        this.exemptionService = exemptionService;

    }
  
    
    @PostMapping("/add-exemptions")
    public Exemption createExemption(@RequestBody Exemption exemption) {
        return exemptionService.createExemption(exemption);
    }
    
    
}