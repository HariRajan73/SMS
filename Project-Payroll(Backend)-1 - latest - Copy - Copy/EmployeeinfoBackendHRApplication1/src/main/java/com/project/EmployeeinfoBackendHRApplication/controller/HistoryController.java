package com.project.EmployeeinfoBackendHRApplication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.entity.History;
import com.project.EmployeeinfoBackendHRApplication.service.HistoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    
    @PostMapping("/history")
    public ResponseEntity<Object> createHistory(@RequestBody History history) {
        try {
            historyService.validateAndCreateHistory(history);

            String successMessage = "Successfully updated no.of worked days";
            return new ResponseEntity<>(Map.of("Response", successMessage), HttpStatus.OK);
        } catch (IllegalStateException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(Map.of("error", errorMessage), HttpStatus.BAD_REQUEST);
        }
    }
}
