package com.project.EmployeeinfoBackendHRApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.WorkingDays;
import com.project.EmployeeinfoBackendHRApplication.repository.WorkingDaysRepo;

@Service
public class WorkingDaysService {
	private final WorkingDaysRepo workingDaysRepo;

    @Autowired
    public WorkingDaysService(WorkingDaysRepo workingDaysRepo) {
        this.workingDaysRepo = workingDaysRepo;
    }

    public WorkingDays createWorkingDays(WorkingDays workingDays) {
        return workingDaysRepo.save(workingDays);
    }

    
    


}
