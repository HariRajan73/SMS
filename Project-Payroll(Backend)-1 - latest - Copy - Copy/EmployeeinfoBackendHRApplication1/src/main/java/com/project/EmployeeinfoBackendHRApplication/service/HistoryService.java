package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.History;
import com.project.EmployeeinfoBackendHRApplication.entity.WorkingDays;
import com.project.EmployeeinfoBackendHRApplication.repository.HistoryRepo;
import com.project.EmployeeinfoBackendHRApplication.repository.WorkingDaysRepo;

@Service
public class HistoryService {

    private final HistoryRepo historyRepository;
    private final WorkingDaysRepo workingDaysRepo;

    @Autowired
    public HistoryService(HistoryRepo historyRepository, WorkingDaysRepo workingDaysRepo) {
        this.historyRepository = historyRepository;
        this.workingDaysRepo = workingDaysRepo;
    }

    public void validateAndCreateHistory(History history) {
    	int rnNo = history.getRnNo();
        String month = history.getMonth();
        int year = history.getYear();
        int workedDays = history.getNumberOfWorkedDays();
        int totalWorkingDays = workingDaysRepo.getTotalWorkingDaysForMonthAndYear(month, year);

        if (historyRepository.existsByRnNoAndMonthAndYear(rnNo, month, year)) {
            throw new IllegalStateException("Record already exists.");
        }
        
        if (workedDays > totalWorkingDays) {
            throw new IllegalStateException("Worked days cannot be greater than total working days.");
        }

        historyRepository.save(history);
    }
    
    public boolean existsByRnNoAndMonthAndYear(int rnNo, String month, int year, Long exemptionId) {
        return historyRepository.existsByRnNoAndMonthAndYear(rnNo, month, year);
    }
}
