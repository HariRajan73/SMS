package com.project.EmployeeinfoBackendHRApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int rnNo;
	private String month;
	private int year;
	private String pslocation;
	private int numberOfWorkedDays;
	private double basicSalary;
	private double exemptions;
	private double monthlyExemptions;
	private double monthlySalary;
	private double monthlyTax;
	private double totalTax;
    private double vpfValue;
    private double epfValue;
    private double hra;
    private double specialAllowance;
    private double grossTotal;
    private double netPay;   
    private double dTotal;      
    
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(long id, int rnNo, String month, int year, String pslocation, int numberOfWorkedDays,
			double basicSalary, double exemptions, double monthlyExemptions, double monthlySalary, double monthlyTax,
			double totalTax, double vpfValue, double epfValue, double hra, double specialAllowance, double grossTotal,
			double netPay, double dTotal) {
		super();
		this.id = id;
		this.rnNo = rnNo;
		this.month = month;
		this.year = year;
		this.pslocation = pslocation;
		this.numberOfWorkedDays = numberOfWorkedDays;
		this.basicSalary = basicSalary;
		this.exemptions = exemptions;
		this.monthlyExemptions = monthlyExemptions;
		this.monthlySalary = monthlySalary;
		this.monthlyTax = monthlyTax;
		this.totalTax = totalTax;
		this.vpfValue = vpfValue;
		this.epfValue = epfValue;
		this.hra = hra;
		this.specialAllowance = specialAllowance;
		this.grossTotal = grossTotal;
		this.netPay = netPay;
		this.dTotal = dTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRnNo() {
		return rnNo;
	}

	public void setRnNo(int rnNo) {
		this.rnNo = rnNo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPslocation() {
		return pslocation;
	}

	public void setPslocation(String pslocation) {
		this.pslocation = pslocation;
	}

	public int getNumberOfWorkedDays() {
		return numberOfWorkedDays;
	}

	public void setNumberOfWorkedDays(int numberOfWorkedDays) {
		this.numberOfWorkedDays = numberOfWorkedDays;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getExemptions() {
		return exemptions;
	}

	public void setExemptions(double exemptions) {
		this.exemptions = exemptions;
	}

	public double getMonthlyExemptions() {
		return monthlyExemptions;
	}

	public void setMonthlyExemptions(double monthlyExemptions) {
		this.monthlyExemptions = monthlyExemptions;
	}

	public double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public double getMonthlyTax() {
		return monthlyTax;
	}

	public void setMonthlyTax(double monthlyTax) {
		this.monthlyTax = monthlyTax;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public double getVpfValue() {
		return vpfValue;
	}

	public void setVpfValue(double vpfValue) {
		this.vpfValue = vpfValue;
	}

	public double getEpfValue() {
		return epfValue;
	}

	public void setEpfValue(double epfValue) {
		this.epfValue = epfValue;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public double getGrossTotal() {
		return grossTotal;
	}

	public void setGrossTotal(double grossTotal) {
		this.grossTotal = grossTotal;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public double getdTotal() {
		return dTotal;
	}

	public void setdTotal(double dTotal) {
		this.dTotal = dTotal;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", rnNo=" + rnNo + ", month=" + month + ", year=" + year + ", pslocation="
				+ pslocation + ", numberOfWorkedDays=" + numberOfWorkedDays + ", basicSalary=" + basicSalary
				+ ", exemptions=" + exemptions + ", monthlyExemptions=" + monthlyExemptions + ", monthlySalary="
				+ monthlySalary + ", monthlyTax=" + monthlyTax + ", totalTax=" + totalTax + ", vpfValue=" + vpfValue
				+ ", epfValue=" + epfValue + ", hra=" + hra + ", specialAllowance=" + specialAllowance + ", grossTotal="
				+ grossTotal + ", netPay=" + netPay + ", dTotal=" + dTotal + "]";
	}

	
}
