package com.example.model;

public class Graduation {

	private String division;
	private Integer yearOfPassing;

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

	@Override
	public String toString() {
		return "Graduation [division= " + division + ", yearOfPassing= " + yearOfPassing + "]";
	}

}
