package com.project.EmployeeinfoBackendHRApplication.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.project.EmployeeinfoBackendHRApplication.entity.History;
import com.project.EmployeeinfoBackendHRApplication.repository.EmployeeRepository;
import com.project.EmployeeinfoBackendHRApplication.repository.HistoryRepo;

@Service
public class PdfFileService {
	
	private final HistoryRepo historyRepo;
	private final EmployeeRepository employeeRepo;

    @Autowired
    public PdfFileService(HistoryRepo historyRepo , EmployeeRepository employeeRepo) {
        this.historyRepo = historyRepo;
        this.employeeRepo = employeeRepo;

    }

    public void generatePdf(int rnNo, String month, int year, int numberOfWorkedDays,
                            double basicSalary, double monthlyExemptions, double monthlySalary,
                            double monthlyTax, double vpfValue ,double epfValue, double hra, double specialAllowance,
                            double grossTotal, double netPay ,double dTotal, String name ,String department ,
                            String doj, long uan_num, String pan_num, String bank_name,
                            long acc_num  
                            ) {
    	
        String currentMonth = month.replace(" ", "_");

        String fileName = rnNo + "_" + currentMonth + "_" + year + ".pdf";

        String basePath = "C:/Users/z041422/OneDrive - Alliance/Desktop/SMS/PDF";

        String filePath = basePath + "/" + fileName;
        
            try (PdfWriter pdfWriter = new PdfWriter(filePath);
                    PdfDocument pdfDoc = new PdfDocument(pdfWriter);
                    Document document = new Document(pdfDoc)) {

                   Table table = new Table(2);
                   
                   Cell headerCell = new Cell(1, 2);
                   headerCell.add(createHeaderParagraph("PAYSLIP FOR "+ month + "-" +year));
                   table.addHeaderCell(headerCell);
             
                   table.addCell(createCell("Name:", name));
                   table.addCell(createCell("PAN:", pan_num));
                   
                   table.addCell(createCell("RN_No:", rnNo));
                   table.addCell(createCell("UAN:", uan_num));
                   
                   table.addCell(createCell("Acc_Number:", acc_num));
                   table.addCell(createCell("Bank_Name:", bank_name));
                   
                   table.addCell(createCell("DOJ:", doj));
                   table.addCell(createCell("No.of Worked Days :", numberOfWorkedDays));
                 
                   Cell lastCell1 = new Cell(1, 2);
                   lastCell1.add(createNestedTable("Earnings:Amount(Rs.)", "Deductions:Amount(Rs.)"));
                   table.addCell(lastCell1);
                   
                   table.addCell(createCell("Basic :", basicSalary));
                   table.addCell(createCell("Employee Provident Fund:", epfValue));
                   
                   table.addCell(createCell("House Rent Allowance:", hra));
                   table.addCell(createCell("Voluntary Provident Fund:", vpfValue));
                   
                   table.addCell(createCell("Special Allowances:", specialAllowance));
                   table.addCell(createCell("Tax paid(*Exemptions included):", monthlyTax));
                   
                   table.addCell(createCell("Gross Total :", grossTotal));
                   table.addCell(createCell("Deductions Total :", dTotal));
                   
                   table.addCell(createSpannedCell("Net Pay:", netPay, 2));
                         
                   document.add(table);
                   
                   document.add(new Paragraph("* This is a computer-generated pay slip, signature not required"));

            }
            
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }}
    
          
      
    
    private static Table createNestedTable(String column1Text, String column2Text) {
        Table nestedTable = new Table(2);

        nestedTable.addCell(column1Text).setBold().setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(column2Text).setBold().setTextAlignment(TextAlignment.CENTER);

        return nestedTable;
    }   
    
    private static Paragraph createHeaderParagraph(String headerText) {
        return new Paragraph(headerText).setBold().setTextAlignment(TextAlignment.CENTER);
    }
    
           private static Cell createCell(String label, Object value) {
               Cell cell = new Cell();
               cell.add(new Paragraph(label));
               cell.add(new Paragraph(String.valueOf(value)));
               return cell;
           }          
           
           private static Cell createSpannedCell(String label, Object value, int colSpan) {
        	    Cell cell = new Cell(1, colSpan);
        	    cell.add(new Paragraph(label));
                cell.add(new Paragraph(String.valueOf(value)));
        	    return cell;
        	}

    public String getPdfFilePath(int rnNo, String month, int year) {
        List<History> historyList = historyRepo.findByRnNoAndMonthAndYear(rnNo, month, year);

        if (!historyList.isEmpty()) {
            History history = historyList.get(0);
            return history.getPslocation();
        }

        System.out.println("Payslip location not found...");
        return null;
    }


	
}
