package com.project.hms.model;

public class DiagnosisDto {

	private Integer testId;
	private String testName;
	private String testDescription;
	private Double testCharges;
	
	public DiagnosisDto( String testName, String testDescription, Double testCharges) {
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
