package com.project.EmployeeinfoBackendHRApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Exemption;
import com.project.EmployeeinfoBackendHRApplication.repository.ExemptionRepo;

@Service
public class ExemptionService {

    private final ExemptionRepo exemptionRepo;

    @Autowired
    public ExemptionService(ExemptionRepo exemptionRepo) {
        this.exemptionRepo = exemptionRepo;
    }
    
    public Exemption createExemption(Exemption exemption) {
        return exemptionRepo.save(exemption);
    }
    
    public List<Exemption> getExemptionsByLocation(String location) {
        return exemptionRepo.findByLocation(location);
    }
    
}
