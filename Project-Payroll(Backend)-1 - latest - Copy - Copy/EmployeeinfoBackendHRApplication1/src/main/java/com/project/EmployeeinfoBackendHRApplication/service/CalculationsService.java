package com.project.EmployeeinfoBackendHRApplication.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;
import com.project.EmployeeinfoBackendHRApplication.entity.History;
import com.project.EmployeeinfoBackendHRApplication.entity.SelectedExemptions;
import com.project.EmployeeinfoBackendHRApplication.entity.TaxSlab;
import com.project.EmployeeinfoBackendHRApplication.entity.WorkingDays;
import com.project.EmployeeinfoBackendHRApplication.repository.EmployeeRepository;
import com.project.EmployeeinfoBackendHRApplication.repository.ExemptionRepo;
import com.project.EmployeeinfoBackendHRApplication.repository.HistoryRepo;
import com.project.EmployeeinfoBackendHRApplication.repository.SelectedExemptionsRepo;
import com.project.EmployeeinfoBackendHRApplication.repository.TaxSlabRepository;
import com.project.EmployeeinfoBackendHRApplication.repository.WorkingDaysRepo;

@Service
public class CalculationsService {

    private final EmployeeRepository employeeRepository;
    private final TaxSlabRepository taxSlabRepository;
    private final ExemptionRepo exemptionRepo;
    private final HistoryRepo historyRepo;
    private final WorkingDaysRepo workingDaysRepo;
    private final PdfFileService pdfFileService; 
    private final SelectedExemptionsRepo selectedExemeptionsRepo;

    @Autowired
    public CalculationsService(EmployeeRepository employeeRepository, TaxSlabRepository taxSlabRepository,
            ExemptionRepo exemptionRepo, HistoryRepo historyRepo, WorkingDaysRepo workingDaysRepo,
            PdfFileService pdfFileService , SelectedExemptionsRepo selectedExemeptionsRepo
            ) {
        this.employeeRepository = employeeRepository;
        this.taxSlabRepository = taxSlabRepository;
        this.exemptionRepo = exemptionRepo;
        this.historyRepo = historyRepo;
        this.workingDaysRepo = workingDaysRepo;
        this.pdfFileService = pdfFileService;
        this.selectedExemeptionsRepo = selectedExemeptionsRepo;
    }

    public void calculateAndSaveDataForAllEmployees(String month , int year) throws Exception {
        List<History> histories = historyRepo.findByMonthAndYear(month,year);

        for (History history : histories) {
            int rnNo = history.getRnNo();
            Optional<Employee> optionalEmployee = employeeRepository.findByRnNo(rnNo);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                double ctc = employee.getCtc();

                double additionalExemptions = calculateAdditionalExemptions(employee,year,history);
                double exemptions = additionalExemptions;

                double totalTax = calculateTax(employee, ctc, exemptions, employee.getTaxType());

                double vpfPercentage = employee.getVpfPercentage();

                double monthlyExemptions = exemptions / 12;
                
                double monthlyTax = (totalTax / 12) - monthlyExemptions;

                if (monthlyTax <= 0) {
                    monthlyTax = 0;
                } else {
                    monthlyTax = (totalTax / 12) - monthlyExemptions;
                }
                
                List<History> historyList = historyRepo.findByRnNoAndMonthAndYear(employee.getRnNo(), month, year);

             History history1 = historyList.isEmpty() ? null : historyList.get(0);

                Optional<WorkingDays> optionalWorkingDays = workingDaysRepo
                        .findByMonthAndYear(history.getMonth(), history.getYear());
                WorkingDays workingDays = optionalWorkingDays.orElse(null);

                int numberOfWorkedDays = history.getNumberOfWorkedDays();

                double monthlySalary = ctc / 12;

                if (numberOfWorkedDays < workingDays.getTotalworkingdaysformonth()) {
                    monthlySalary = (monthlySalary / workingDays.getTotalworkingdaysformonth()) * numberOfWorkedDays;
                } else if (numberOfWorkedDays > workingDays.getTotalworkingdaysformonth()) {
                	throw new IllegalArgumentException("NumberOfWorkedDays cannot be greater than Totalworkingdaysformonth,RnNo: " + rnNo);
                } else {
                    monthlySalary = (ctc / 12);
                }

                double basicSalary = 0.4 * monthlySalary;
                double hra = 0.4 * monthlySalary;
                double specialAllowance = 0.2 * monthlySalary;
                double epfValue = 0.12 * basicSalary;
                double grossTotal = basicSalary + hra + specialAllowance;
                double vpfValue = (vpfPercentage / 100) * basicSalary;
//                double dailyWage = monthlySalary / numberOfWorkedDays;
                double dTotal = epfValue + vpfValue + monthlyTax;
                double netPay = grossTotal - dTotal;

                // FOR DECIMAL FORMAT
                monthlyTax = parseDoubleWithFormat(monthlyTax);
                monthlyExemptions = parseDoubleWithFormat(monthlyExemptions);
                monthlySalary = parseDoubleWithFormat(monthlySalary);
//                dailyWage = parseDoubleWithFormat(dailyWage);
                basicSalary = parseDoubleWithFormat(basicSalary);
                vpfValue = parseDoubleWithFormat(vpfValue);
                epfValue = parseDoubleWithFormat(epfValue);
                ctc = parseDoubleWithFormat(ctc);
                totalTax = parseDoubleWithFormat(totalTax);
                exemptions = parseDoubleWithFormat(exemptions);
                hra = parseDoubleWithFormat(hra);
                specialAllowance = parseDoubleWithFormat(specialAllowance);
                grossTotal = parseDoubleWithFormat(grossTotal);
                netPay = parseDoubleWithFormat(netPay);
                dTotal = parseDoubleWithFormat(dTotal);


                history.setTotalTax(totalTax);
                history.setMonthlyExemptions(monthlyExemptions);
                history.setMonthlyTax(monthlyTax);
                history.setMonthlySalary(monthlySalary);
                history.setBasicSalary(basicSalary);
                history.setExemptions(exemptions);
                history.setVpfValue(vpfValue);
                history.setEpfValue(epfValue);
                history.setHra(hra);
                history.setSpecialAllowance(specialAllowance);
                history.setGrossTotal(grossTotal);
                history.setNetPay(netPay);   
                history.setdTotal(dTotal);
                
                String currentMonth = month.replace(" ", "_");

                String fileName = rnNo + "_" + currentMonth + "_" + year + ".pdf";

                String basePath = "C:/Users/z041422/OneDrive - Alliance/Desktop/SMS/PDF";

                String filePath = basePath + "/" + fileName;
                
                history.setPslocation(filePath);

                historyRepo.save(history);

                 //Generate PDF
                pdfFileService.generatePdf(
                        rnNo,month,year,
                        history.getNumberOfWorkedDays(), history.getBasicSalary(),history.getMonthlyExemptions(),
                        history.getMonthlySalary(),history.getMonthlyTax(),history.getVpfValue(),history.getEpfValue(),
                        history.getHra(), history.getSpecialAllowance() , history.getGrossTotal() ,history.getNetPay() ,history.getdTotal(),
                        employee.getName(),employee.getDepartment(),
                        employee.getDoj(),employee.getUan_num(),employee.getPan_num(),employee.getBank_name(),employee.getAcc_num()                   
                        
                );
            }
        }
    }
  
    private double calculateAdditionalExemptions(Employee employee , int year ,History history) {
        int rnNo = employee.getRnNo();
        String month=history.getMonth();
        List<SelectedExemptions> selectedExemptionsList = selectedExemeptionsRepo.findByRnNoAndYearAndMonth(rnNo,year,month);

        // Calculation of sum of exeValue for the given rnNo
        double totalExeValue = selectedExemptionsList.stream()
                .mapToDouble(SelectedExemptions::getExeValue)
                .sum();

        return totalExeValue;
    }
    

    // TAX CALCULATION
    private double calculateTax(Employee employee, double ctc, double exemptions, String taxType) {
        List<TaxSlab> taxSlabs = taxSlabRepository.findAll();
        double taxableIncome = ctc - exemptions; 
        double totalTax = 0;

        for (TaxSlab taxSlab : taxSlabs) {
            double minIncome = taxSlab.getMinIncome();
            double maxIncome = taxSlab.getMaxIncome();

            if (taxType.equals(taxSlab.getTaxType())) {
                double taxableAmountInCurrentRange = Math.min(maxIncome, taxableIncome) - minIncome;
                taxableAmountInCurrentRange = Math.max(taxableAmountInCurrentRange, 0);

                double taxInCurrentRange = (taxableAmountInCurrentRange * taxSlab.getTaxRate()) / 100;
                totalTax += taxInCurrentRange; 

                if (taxableIncome <= 0) {
                    break;
                }
            }
        }

        return totalTax;
    }

    private double parseDoubleWithFormat(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }
}


