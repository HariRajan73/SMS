package com.project.EmployeeinfoBackendHRApplication.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class WorkingDays {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String month;
	private int year;
	private int totalworkingdaysformonth;
	public WorkingDays() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WorkingDays(long id, String month, int year, int totalworkingdaysformonth) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
		this.totalworkingdaysformonth = totalworkingdaysformonth;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getTotalworkingdaysformonth() {
		return totalworkingdaysformonth;
	}
	public void setTotalworkingdaysformonth(int totalworkingdaysformonth) {
		this.totalworkingdaysformonth = totalworkingdaysformonth;
	}
	@Override
	public String toString() {
		return "WorkingDays [id=" + id + ", month=" + month + ", year=" + year + ", totalworkingdaysformonth="
				+ totalworkingdaysformonth + "]";
	}
	
	public boolean isValidWorkedDays(int numberOfWorkedDays) {
        return numberOfWorkedDays <= totalworkingdaysformonth;
    }
	
	
	
	
}
