package com.project.EmployeeinfoBackendHRApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "selected_exemptions")
public class SelectedExemptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rnNo;
    
    @ManyToOne
    @JoinColumn(name = "exemption_id", referencedColumnName = "id")
    private Exemption exemption;

    private int year;

    @Column(name = "exe_value")
    private int exeValue;
    
	private String currentlocation;
	
	private String month;
    
    public Long getExemptionId() {
        return exemption != null ? exemption.getId() : null;
    }

	public SelectedExemptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectedExemptions(long id, int rnNo, Exemption exemption, int year, int exeValue, String currentlocation,
			String month) {
		super();
		this.id = id;
		this.rnNo = rnNo;
		this.exemption = exemption;
		this.year = year;
		this.exeValue = exeValue;
		this.currentlocation = currentlocation;
		this.month = month;
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

	public Exemption getExemption() {
		return exemption;
	}

	public void setExemption(Exemption exemption) {
		this.exemption = exemption;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getExeValue() {
		return exeValue;
	}

	public void setExeValue(int exeValue) {
		this.exeValue = exeValue;
	}

	public String getCurrentlocation() {
		return currentlocation;
	}

	public void setCurrentlocation(String currentlocation) {
		this.currentlocation = currentlocation;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "SelectedExemptions [id=" + id + ", rnNo=" + rnNo + ", exemption=" + exemption + ", year=" + year
				+ ", exeValue=" + exeValue + ", currentlocation=" + currentlocation + ", month=" + month + "]";
	}

	
}
