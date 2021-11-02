package com.project.hms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="diagnostics_conducted")
public class DiagnosticsConducted {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer diagnosticsConductedId;
	
	private Integer patientId;
	private Integer testId;
	private LocalDate dateOfDiagnosis;
	private String result;
	private String commentOnResult;
	
	public DiagnosticsConducted(Integer patientId, Integer testId, String result, String commentOnResult) {
		super();
		this.patientId = patientId;
		this.testId = testId;
		this.result = result;
		this.commentOnResult = commentOnResult;
		this.dateOfDiagnosis = LocalDate.now();
	}

	public Integer getDiagnosticsConductedId() {
		return diagnosticsConductedId;
	}

	public void setDiagnosticsConductedId(Integer diagnosticsConductedId) {
		this.diagnosticsConductedId = diagnosticsConductedId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public LocalDate getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}

	public void setDateOfDiagnosis(LocalDate dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCommentOnResult() {
		return commentOnResult;
	}

	public void setCommentOnResult(String commentOnResult) {
		this.commentOnResult = commentOnResult;
	}
	
	
}
