package com.linkedrh.training.class_.dtos;

import java.time.LocalDate;

public class ClassCreateDTO {
	public LocalDate start, end;
	public String location;
	public int courseCode;

	public ClassCreateDTO(LocalDate start, LocalDate end, String location, int courseCode) {
		this.start = start;
		this.end = end;
		this.location = location;
		this.courseCode = courseCode;
	}
}
