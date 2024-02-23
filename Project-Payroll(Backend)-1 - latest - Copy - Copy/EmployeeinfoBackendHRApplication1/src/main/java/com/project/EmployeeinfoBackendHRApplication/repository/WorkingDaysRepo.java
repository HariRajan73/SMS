package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.WorkingDays;

@Repository
public interface WorkingDaysRepo extends JpaRepository<WorkingDays, Long> {
    Optional<WorkingDays> findByMonthAndYear(String month, int year);

    @Query("SELECT w.totalworkingdaysformonth FROM WorkingDays w WHERE w.month = ?1 AND w.year = ?2")
	int getTotalWorkingDaysForMonthAndYear(String month, int year);



}



