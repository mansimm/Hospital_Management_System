package com.project.hms.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
	
	private String ErrorMessage;
	private Integer ErrorCode;
	private LocalDateTime timestamp;
	
	public ErrorInfo() {
		super();
	}
	
	public ErrorInfo(String errorMessage, Integer errorCode, LocalDateTime timestamp) {
		super();
		ErrorMessage = errorMessage;
		ErrorCode = errorCode;
		this.timestamp = timestamp;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public Integer getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(Integer errorCode) {
		ErrorCode = errorCode;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
