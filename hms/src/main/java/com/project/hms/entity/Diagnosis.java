package com.project.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="diagnosis")
public class Diagnosis {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer testId;
	private String testName;
	private String testDescription;
	private Double testCharges;
	
	public Diagnosis( String testName, String testDescription, Double testCharges) {
		this.testName = testName;
		this.testDescription = testDescription;
		this.testCharges = testCharges;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public Double getTestCharges() {
		return testCharges;
	}

	public void setTestCharges(Double testCharges) {
		this.testCharges = testCharges;
	}
	
	
}
