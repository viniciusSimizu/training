package com.linkedrh.training.class_.dtos;

import java.time.LocalDate;

public class ClassCreateDTO {
	public LocalDate startDate, endDate;
	public String location;
	public int courseCode;

	public ClassCreateDTO(LocalDate startDate, LocalDate endDate, String location, int courseCode) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.courseCode = courseCode;
	}
}
