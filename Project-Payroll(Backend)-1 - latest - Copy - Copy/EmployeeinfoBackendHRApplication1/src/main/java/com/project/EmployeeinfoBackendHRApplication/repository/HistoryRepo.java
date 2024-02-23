package com.project.EmployeeinfoBackendHRApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.EmployeeinfoBackendHRApplication.entity.History;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {

    @Query("SELECT h.numberOfWorkedDays FROM History h WHERE h.rnNo = :rnNo")
    int getNumberOfWorkedDaysByRnNo(int rnNo);

    Optional<History> findByRnNo(int rnNo);
    
    List<History> findByMonthAndYear(String month, int year);

    List<History> findByRnNoAndMonthAndYear(int rnNo, String month, int year);

	boolean existsByMonthAndYear(String month, int year);
	
    int countByMonthAndYear(String month, int year);
    
    boolean existsByRnNoAndMonthAndYear(int rnNo, String month, int year);



}
