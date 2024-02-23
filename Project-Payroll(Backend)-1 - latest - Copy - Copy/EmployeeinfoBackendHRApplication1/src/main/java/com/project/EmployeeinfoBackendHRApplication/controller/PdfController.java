package com.project.EmployeeinfoBackendHRApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.service.PdfFileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PdfController {

    private final PdfFileService pdfFileService;

    @Autowired
    public PdfController(PdfFileService pdfFileService) {
        this.pdfFileService = pdfFileService;
    }

    @GetMapping("/downloadPdf/{rnNo}/{month}/{year}")
    public ResponseEntity<Resource> downloadPdf(
            @PathVariable int rnNo,
            @PathVariable String month,
            @PathVariable int year) {
        String filePath = pdfFileService.getPdfFilePath(rnNo, month, year);

        if (filePath != null) {
            // Load file as Resource
            Resource fileResource = new FileSystemResource(filePath);

            MediaType mediaType = MediaType.APPLICATION_PDF;

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResource.getFilename())
                    .contentType(mediaType)
                    .body(fileResource);
        } else {
            System.out.println("File path not found");
            return ResponseEntity.notFound().build();
        }
    }
}
