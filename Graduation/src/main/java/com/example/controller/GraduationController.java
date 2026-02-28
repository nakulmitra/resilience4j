package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Graduation;

@RestController
@RequestMapping("/graduation")
public class GraduationController {

	private static List<Graduation> graduationList = new ArrayList<>(
			Arrays.asList(new Graduation(1, "I - Division", 2021), new Graduation(2, "I - Division", 2021),
					new Graduation(3, "II - Division", 2021)));

	@GetMapping("/getUserGraduationDataById/{id}")
	public Graduation getUserGraduationDataById(@PathVariable(value = "id") Integer id) {
		for (Graduation obj : graduationList) {
			if (obj.getUserId().equals(id)) {
				return obj;
			}
		}

		return null;
	}
}
