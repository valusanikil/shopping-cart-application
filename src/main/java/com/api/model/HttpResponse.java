package com.api.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HttpResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yy h:mm:ss a z", timezone = "Asia/Kolkata")
	private Date timestamp;
	private int httpStatusCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	
	public HttpResponse() {}

	public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
		this.timestamp = new Date();
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(httpStatus, httpStatusCode, message, reason, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HttpResponse other = (HttpResponse) obj;
		return httpStatus == other.httpStatus && httpStatusCode == other.httpStatusCode
				&& Objects.equals(message, other.message) && Objects.equals(reason, other.reason)
				&& Objects.equals(timestamp, other.timestamp);
	}
	
	
}
