package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.SelectedExemptions;

@Repository
public interface SelectedExemptionsRepo extends JpaRepository<SelectedExemptions, Long>{

	List<SelectedExemptions> findByRnNoAndYearAndMonth(int rnNo, int year,String month);

    boolean existsByRnNoAndMonthAndYearAndExemption_Id(int rnNo, String month, int year, Long exemptionId);

}
