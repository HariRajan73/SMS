package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.TaxSlab;

@Repository
public interface TaxSlabRepository  extends JpaRepository<TaxSlab, Long>{
    List<TaxSlab> findByTaxType(String taxType);

}
