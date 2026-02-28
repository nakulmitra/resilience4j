package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {

	private static List<Company> companyList = new ArrayList<>(
			Arrays.asList(new Company(1, "XYZ", 2021, "Backend - Developer"), new Company(3, "PQR", 2022, "DBA")));

	@GetMapping("/getUserCompanyDataById/{id}")
	public Company getUserCompanyDataById(@PathVariable(value = "id") Integer id) throws InterruptedException {
		Thread t = new Thread();
		t.sleep(6000);
		
		for (Company obj : companyList) {
			if (obj.getUserId().equals(id)) {
				return obj;
			}
		}
		return null;
	}

}
