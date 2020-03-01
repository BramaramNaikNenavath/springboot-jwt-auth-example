package com.tek.models;

public class ResponseModel {
	private String status;
	private String statusText;
	private String userName;
	private String token;
	
	public ResponseModel(String status, String statusText, String userName, String token) {
		this.status = status;
		this.statusText = statusText;
		this.userName = userName;
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
