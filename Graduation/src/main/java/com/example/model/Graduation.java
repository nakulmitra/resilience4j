package com.example.model;

public class Graduation {

	private Integer userId;
	private String division;
	private Integer yearOfPassing;

	public Graduation() {
		super();
	}

	public Graduation(Integer userId, String division, Integer yearOfPassing) {
		super();
		this.userId = userId;
		this.division = division;
		this.yearOfPassing = yearOfPassing;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Integer getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(Integer yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

}
