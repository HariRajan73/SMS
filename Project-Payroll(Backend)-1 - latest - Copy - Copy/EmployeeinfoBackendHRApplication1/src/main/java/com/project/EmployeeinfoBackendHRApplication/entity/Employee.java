package com.project.EmployeeinfoBackendHRApplication.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "employees")
@JsonIgnoreType
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private int rnNo;
	private String employeepassword;
	private String name;
	private String department;
	private String role;
	private String email;
	private long phoneno;
	private String doj;
	private long uan_num;
	private String pan_num;
	private String bank_name;
	private long acc_num;
	private String taxType;
    private int vpfPercentage;
    private int managerRnNo;
    private int age;
    private double ctc;

	public Employee (int rnNo) {
		this.rnNo = rnNo;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int rnNo, String employeepassword, String name, String department, String role, String email,
			long phoneno, String doj, long uan_num, String pan_num, String bank_name, long acc_num, String taxType,
			int vpfPercentage, int managerRnNo, int age, double ctc) {
		super();
		this.rnNo = rnNo;
		this.employeepassword = employeepassword;
		this.name = name;
		this.department = department;
		this.role = role;
		this.email = email;
		this.phoneno = phoneno;
		this.doj = doj;
		this.uan_num = uan_num;
		this.pan_num = pan_num;
		this.bank_name = bank_name;
		this.acc_num = acc_num;
		this.taxType = taxType;
		this.vpfPercentage = vpfPercentage;
		this.managerRnNo = managerRnNo;
		this.age = age;
		this.ctc = ctc;
	}

	public int getRnNo() {
		return rnNo;
	}

	public void setRnNo(int rnNo) {
		this.rnNo = rnNo;
	}

	public String getEmployeepassword() {
		return employeepassword;
	}

	public void setEmployeepassword(String employeepassword) {
		this.employeepassword = employeepassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public long getUan_num() {
		return uan_num;
	}

	public void setUan_num(long uan_num) {
		this.uan_num = uan_num;
	}

	public String getPan_num() {
		return pan_num;
	}

	public void setPan_num(String pan_num) {
		this.pan_num = pan_num;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public long getAcc_num() {
		return acc_num;
	}

	public void setAcc_num(long acc_num) {
		this.acc_num = acc_num;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public int getVpfPercentage() {
		return vpfPercentage;
	}

	public void setVpfPercentage(int vpfPercentage) {
		this.vpfPercentage = vpfPercentage;
	}

	public int getManagerRnNo() {
		return managerRnNo;
	}

	public void setManagerRnNo(int managerRnNo) {
		this.managerRnNo = managerRnNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCtc() {
		return ctc;
	}

	public void setCtc(double ctc) {
		this.ctc = ctc;
	}

	@Override
	public String toString() {
		return "Employee [rnNo=" + rnNo + ", employeepassword=" + employeepassword + ", name=" + name + ", department="
				+ department + ", role=" + role + ", email=" + email + ", phoneno=" + phoneno + ", doj=" + doj
				+ ", uan_num=" + uan_num + ", pan_num=" + pan_num + ", bank_name=" + bank_name + ", acc_num=" + acc_num
				+ ", taxType=" + taxType + ", vpfPercentage=" + vpfPercentage + ", managerRnNo=" + managerRnNo
				+ ", age=" + age + ", ctc=" + ctc + "]";
	}

	
}
