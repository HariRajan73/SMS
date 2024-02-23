package com.project.EmployeeinfoBackendHRApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TaxSlab {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private double minIncome;
 private double maxIncome;
 private double taxRate;
 private String taxType;
public TaxSlab() {
	super();
	// TODO Auto-generated constructor stub
}
public TaxSlab(Long id, double minIncome, double maxIncome, double taxRate, String taxType) {
	super();
	this.id = id;
	this.minIncome = minIncome;
	this.maxIncome = maxIncome;
	this.taxRate = taxRate;
	this.taxType = taxType;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public double getMinIncome() {
	return minIncome;
}
public void setMinIncome(double minIncome) {
	this.minIncome = minIncome;
}
public double getMaxIncome() {
	return maxIncome;
}
public void setMaxIncome(double maxIncome) {
	this.maxIncome = maxIncome;
}
public double getTaxRate() {
	return taxRate;
}
public void setTaxRate(double taxRate) {
	this.taxRate = taxRate;
}
public String getTaxType() {
	return taxType;
}
public void setTaxType(String taxType) {
	this.taxType = taxType;
}
@Override
public String toString() {
	return "TaxSlab [id=" + id + ", minIncome=" + minIncome + ", maxIncome=" + maxIncome + ", taxRate=" + taxRate
			+ ", taxType=" + taxType + "]";
}

 
}

