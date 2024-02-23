package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.Exemption;

@Repository
public interface ExemptionRepo extends JpaRepository<Exemption, Long> {

    List<Exemption> findByLocation(String location);
    
    Exemption findByLocationAndCategoryAndPercentage(String location, String category, int percentage);



}
