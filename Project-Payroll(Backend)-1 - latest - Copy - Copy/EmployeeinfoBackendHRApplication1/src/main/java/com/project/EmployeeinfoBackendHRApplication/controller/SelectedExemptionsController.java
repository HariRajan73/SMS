package com.project.EmployeeinfoBackendHRApplication.controller;

import java.time.Month;
import java.time.YearMonth;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.entity.Exemption;
import com.project.EmployeeinfoBackendHRApplication.entity.SelectedExemptions;
import com.project.EmployeeinfoBackendHRApplication.service.SelectedExemptionsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SelectedExemptionsController {

    private final SelectedExemptionsService selectedExemptionsService;

    @Autowired
    public SelectedExemptionsController(SelectedExemptionsService selectedExemptionsService) {
        this.selectedExemptionsService = selectedExemptionsService;
    }

    @PostMapping("/selectedExemptions")
    public ResponseEntity<Object> createSelectedExemptions(@RequestBody SelectedExemptions request) {
        Exemption foundExemption = selectedExemptionsService.findExemptionById(request.getExemptionId());

        if (foundExemption != null) {
            for (Month month : Month.values()) {
                YearMonth yearMonth = YearMonth.of(request.getYear(), month);
                String monthAbbreviation = month.name().toUpperCase();
                //substring(0, 3).

                // Check if the record already exists
                if (selectedExemptionsService.existsByRnNoAndMonthAndYearAndExemption_Id(request.getRnNo(), month.name(), request.getYear(), foundExemption.getId())) {
                    String errorMessage = "Record already exists for rnNo: " + request.getRnNo() +
                            ", month: " + month.name() +
                            ", year: " + request.getYear() +
                            ", exemptionId: " + foundExemption.getId();
                    return new ResponseEntity<>(Map.of("error", errorMessage), HttpStatus.CONFLICT);
                }

                SelectedExemptions selectedExemptions = new SelectedExemptions();
                selectedExemptions.setRnNo(request.getRnNo());
                selectedExemptions.setExemption(foundExemption);
                selectedExemptions.setYear(request.getYear());
                selectedExemptions.setExeValue(request.getExeValue());
                selectedExemptions.setCurrentlocation(request.getCurrentlocation());
                selectedExemptions.setMonth(monthAbbreviation);

                selectedExemptionsService.createSelectedExemptions(selectedExemptions);
            }

            String successMessage = "Records created successfully";
            return new ResponseEntity<>(Map.of("message", successMessage), HttpStatus.CREATED);
        } else {
            String errorMessage = "Exemption not found";
            return new ResponseEntity<>(Map.of("error", errorMessage), HttpStatus.NOT_FOUND);
        }
    }
    
    
}

