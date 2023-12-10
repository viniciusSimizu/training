package com.linkedrh.training.class_.dtos;

import java.time.LocalDate;

public class ClassUpdateDTO {
	public LocalDate startDate, endDate;
	public String location;

	public ClassUpdateDTO(LocalDate startDate, LocalDate endDate, String location) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
	}
}
