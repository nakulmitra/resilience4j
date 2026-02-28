package com.example.model;

public class OtherResources {

	private Graduation graduationData;
	private Company companyData;

	public Graduation getGraduationData() {
		return graduationData;
	}

	public void setGraduationData(Graduation graduationData) {
		this.graduationData = graduationData;
	}

	public Company getCompanyData() {
		return companyData;
	}

	public void setCompanyData(Company companyData) {
		this.companyData = companyData;
	}

	@Override
	public String toString() {
		return " OtherResources [graduationData=" + graduationData + ", companyData=" + companyData + "]";
	}

}
