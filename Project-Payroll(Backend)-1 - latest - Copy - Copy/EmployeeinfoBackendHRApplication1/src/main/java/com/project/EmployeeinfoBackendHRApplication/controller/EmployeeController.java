package com.project.EmployeeinfoBackendHRApplication.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeinfoBackendHRApplication.entity.Employee;
import com.project.EmployeeinfoBackendHRApplication.entity.History;
import com.project.EmployeeinfoBackendHRApplication.entity.LoginRequest;
//import com.project.EmployeeinfoBackendHRApplication.exception.ResourceNotFoundException;
import com.project.EmployeeinfoBackendHRApplication.repository.EmployeeRepository;
import com.project.EmployeeinfoBackendHRApplication.repository.HistoryRepo;
import com.project.EmployeeinfoBackendHRApplication.service.CalculationsService;
import com.project.EmployeeinfoBackendHRApplication.service.EmployeeService;
import com.project.EmployeeinfoBackendHRApplication.service.PasswordGenerator;
import com.project.EmployeeinfoBackendHRApplication.service.PwdMailSender;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private PasswordGenerator generator;
	@Autowired
	private MailSender mailsender;   
	@Autowired
	private PwdMailSender pwdmailsender;   
    @Autowired
    private HistoryRepo historyRepo; 
    @Autowired
    private EmployeeService employeeService;
    private final CalculationsService calculationsService;

    @Autowired
    public EmployeeController(CalculationsService calculationsService) {
        this.calculationsService = calculationsService;
    }
       
    @PostMapping("/calculate")
    public ResponseEntity<String> calculateAndSaveDataForAllEmployees(@RequestParam String month,
            @RequestParam int year) {
        try {
        	
//        	List<History> existingRecords = historyRepo.findByMonthAndYear(month, year);
//
//        	if (!existingRecords.isEmpty()) {
//        	    return ResponseEntity.status(HttpStatus.CONFLICT)
//        	            .body("Records for this month and year already exist.");
//        	}

            if (historyRepo.existsByMonthAndYear(month, year)) {
                calculationsService.calculateAndSaveDataForAllEmployees(month, year);
                return ResponseEntity.ok("Generation done successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Enter valid month and year.");
            }

        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during calculation" + e.getMessage());
        }
    
    }
    
	@GetMapping("/addemp")
	public List<Employee> getAllEmployee() {
		return repo.findAll();
	}
	
	@GetMapping("/emphistory/{rnNo}")
	public ResponseEntity<History> getHistory(@PathVariable int rnNo) {
		History history = historyRepo.findByRnNo(rnNo)
				.orElseThrow(() -> new NoSuchElementException("No employee found with rnNo: " + rnNo));
		return ResponseEntity.ok(history);
	}

	
	@PostMapping("/addemp")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

		if (employee == null || employee.getName().isEmpty() || !isValidEmail(employee.getEmail())) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    employee.setRnNo(employee.getRnNo());
	    System.out.println(employee.getRnNo());
	    employee.setEmployeepassword(generator.generateTempPassword(7));
	    Employee savedEmployee = repo.save(employee);

	    mailsender.Mailsender(employee);

	    return ResponseEntity.ok(savedEmployee);
	
	}

	private boolean isValidEmail(String email) {

		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    return email.matches(emailPattern);
	}
	
	
	@GetMapping("/addemp/{rnNo}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int rnNo) {
		Employee employee = repo.findById(rnNo)
				.orElseThrow(() -> new NoSuchElementException("No employee found with id: " + rnNo));
		return ResponseEntity.ok(employee);
	}


	
	@PatchMapping("/addemp/{rnNo}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable int rnNo, @RequestBody Map<String, Object> updates) {
	    Employee employee = repo.findById(rnNo)
	            .orElseThrow(() -> new NoSuchElementException("No employee found with id :" + rnNo));

	    updates.forEach((key, value) -> {
	        switch (key) {

            case "employeepassword":
                employee.setEmployeepassword((String) value);
                break;
            case "name":
                employee.setName((String) value);
                break;
            case "department":
                employee.setDepartment((String) value);
                break;
            case "role":
                employee.setRole((String) value);
                break;
            case "email":
                employee.setEmail((String) value);
                break;
            case "phoneno":
                employee.setPhoneno(((Number) value).longValue()); 
                break;
            case "doj":
                employee.setDoj((String) value);
                break;
            case "uan_num":
                employee.setUan_num(((Number) value).longValue()); 
                break;
            case "pan_num":
                employee.setPan_num((String) value);
                break;
            case "bank_name":
                employee.setBank_name((String) value);
                break;
            case "acc_num":
                employee.setAcc_num(((Number) value).longValue()); 
                break;           
            case "taxType":
                employee.setTaxType((String) value);
                break;
            case "vpfPercentage":
                employee.setVpfPercentage(((Number) value).intValue()); 
                break;
            case "managerRnNo":
                employee.setManagerRnNo(((Number) value).intValue()); 
                break;
            case "age":
                employee.setAge(((Number) value).intValue()); 
                break;
            case "ctc":
                employee.setCtc(((Number) value).doubleValue()); 
                break;
                        
	        }
	        });

	    Employee updatedEmployee = repo.save(employee);
	    return ResponseEntity.ok(updatedEmployee);
	}
	

//	@DeleteMapping("/delemp/{rnNo}")
//	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int rnNo) {
//		Employee employee = repo.findById(rnNo)
//				.orElseThrow(() -> new NoSuchElementException("No employee found with id: " + rnNo));
//		repo.delete(employee);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
	
	
	@GetMapping("/byManagerRnNo/{managerRnNo}")
	public List<Employee> getEmployeesByManagerRnNo(@PathVariable int managerRnNo) {
	    List<Employee> employees = repo.findByManagerRnNo(managerRnNo);

	    if (employees.isEmpty()) {
	        throw new NoSuchElementException("No employees listed.");
	    }
	    return employees;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Employee> login(@RequestBody LoginRequest loginRequest) {
	    try {
	    int username = loginRequest.getUsername();
	    String password = loginRequest.getPassword();

	    Employee employee = employeeService.findByRnNoAndEmployeepassword(username, password);

	    if (employee != null) {
	        String userRole = employee.getRole();

	        if ("EMPLOYEE".equals(userRole)) {
	        	System.out.println("Logged as Employee");
	            return ResponseEntity.ok(employee);
	            
	        } else if ("MANAGER".equals(userRole)) {
	        	System.out.println("Logged as Manager");
	            return ResponseEntity.ok(employee);
	            
	        } else if ("ADMIN".equals(userRole)) {
	        	System.out.println("Logged as Admin");
	            return ResponseEntity.ok(employee);
	        } else {
                throw new IllegalArgumentException("Invalid role");
            }
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

	}
	
//	@PatchMapping("/updatepwd/{rnNo}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable int rnNo, @RequestBody Map<String, Object> updates) {
//        Employee employee = repo.findById(rnNo)
//                .orElseThrow(() -> new NoSuchElementException("No employee found with id :" + rnNo));
//
//        if (employee == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        updates.forEach((key, value) -> {
//            switch (key) {
//                case "employeepassword":
//                    employee.setEmployeepassword((String) value);
//                    break;
//            }
//        });
//
//       
//        employee.setEmployeepassword(generator.generateTempPassword(7));
//        
//        Employee savedEmployee = repo.save(employee);
//
//        pwdmailsender.PwdMailsender(savedEmployee);
//
//        return ResponseEntity.ok(savedEmployee);
//    }


}


