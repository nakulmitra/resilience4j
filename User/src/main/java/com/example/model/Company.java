package com.example.model;

public class Company {

	private String companyName;
	private Integer yearOfJoining;
	private String currentRole;

	public Company() {
		super();
	}

	public Company(String companyName, Integer yearOfJoining, String currentRole) {
		super();
		this.companyName = companyName;
		this.yearOfJoining = yearOfJoining;
		this.currentRole = currentRole;
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

	@Override
	public String toString() {
		return " Company [companyName= " + companyName + ", yearOfJoining= " + yearOfJoining + ", currentRole= "
				+ currentRole + "]";
	}

}
