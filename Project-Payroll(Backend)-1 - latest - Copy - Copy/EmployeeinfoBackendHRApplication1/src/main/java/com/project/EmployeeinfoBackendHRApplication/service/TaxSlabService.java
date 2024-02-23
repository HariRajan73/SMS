package com.project.EmployeeinfoBackendHRApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.TaxSlab;
import com.project.EmployeeinfoBackendHRApplication.repository.TaxSlabRepository;

@Service
public class TaxSlabService {

    private final TaxSlabRepository taxSlabRepository;

    @Autowired
    public TaxSlabService(TaxSlabRepository taxSlabRepository) {
        this.taxSlabRepository = taxSlabRepository;
    }

    public TaxSlab createTaxSlab(TaxSlab taxSlab) {
        return taxSlabRepository.save(taxSlab);
    }
    
    public List<TaxSlab> getTaxSlabsByType(String taxType) {
        return taxSlabRepository.findByTaxType(taxType);
    }

}

