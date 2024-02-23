package com.project.EmployeeinfoBackendHRApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;
import com.project.EmployeeinfoBackendHRApplication.entity.Exemption;
import com.project.EmployeeinfoBackendHRApplication.entity.SelectedExemptions;
import com.project.EmployeeinfoBackendHRApplication.repository.EmployeeRepository;
import com.project.EmployeeinfoBackendHRApplication.repository.ExemptionRepo;
import com.project.EmployeeinfoBackendHRApplication.repository.SelectedExemptionsRepo;

@Service
public class SelectedExemptionsService {

    private final SelectedExemptionsRepo selectedExemptionsRepository;
    private final ExemptionRepo exemptionRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SelectedExemptionsService(SelectedExemptionsRepo selectedExemptionsRepository,
                                     ExemptionRepo exemptionRepository, EmployeeRepository employeeRepository) {
        this.selectedExemptionsRepository = selectedExemptionsRepository;
        this.exemptionRepository = exemptionRepository;
        this.employeeRepository = employeeRepository;
    }

    public SelectedExemptions createSelectedExemptions(SelectedExemptions selectedExemptions) {

        Long exemptionId = selectedExemptions.getExemptionId();
        Exemption foundExemption = findExemptionById(exemptionId);

        if (foundExemption != null) {
            int rnNo = selectedExemptions.getRnNo();
            Employee employee = findEmployeeById(rnNo);
            if (employee != null) {
                double ctc = employee.getCtc();
                double percentage = foundExemption.getPercentage();
                int enteredExeValue = selectedExemptions.getExeValue();

                if (enteredExeValue > ((percentage / 100) * ctc)) {
                    enteredExeValue = (int) ((percentage / 100) * ctc);
//                    System.out.println("Max exemption amount is set automatically");
                }

                selectedExemptions.setExeValue(enteredExeValue);

                return selectedExemptionsRepository.save(selectedExemptions);
            } else {
                System.out.println("Employee not found");
            }
        } else {
            System.out.println("Exemption not found");
        }
        return selectedExemptions;
    }

    public Exemption findExemptionById(Long exemptionId) {
        return exemptionRepository.findById(exemptionId).orElse(null);
    }

    public Employee findEmployeeById(int rnNo) {
        return employeeRepository.findById(rnNo).orElse(null);
    }

    public boolean existsByRnNoAndMonthAndYearAndExemption_Id(int rnNo, String month, int year, Long exemptionId) {
        return selectedExemptionsRepository.existsByRnNoAndMonthAndYearAndExemption_Id(rnNo, month, year, exemptionId);
    }
}

