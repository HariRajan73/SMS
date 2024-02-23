package com.project.EmployeeinfoBackendHRApplication.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "category")
    private String category;

    @Column(name = "percentage")
    private int percentage;
    
   	public Exemption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exemption(Long id, String location, String category, int percentage) {
		super();
		this.id = id;
		this.location = location;
		this.category = category;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Exemption [id=" + id + ", location=" + location + ", category=" + category + ", percentage="
				+ percentage + "]";
	}

	
}

