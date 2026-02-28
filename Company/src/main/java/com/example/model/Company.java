package com.example.model;

public class Company {

	private Integer userId;
	private String companyName;
	private Integer yearOfJoining;
	private String currentRole;

	public Company(Integer userId, String companyName, Integer yearOfJoining, String currentRole) {
		super();
		this.userId = userId;
		this.companyName = companyName;
		this.yearOfJoining = yearOfJoining;
		this.currentRole = currentRole;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(Integer yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

}
