package com.linkedrh.training.course.dtos;

public class CourseUpdateDTO {
	public String name, description;
	public int duration;

	public CourseUpdateDTO(String name, String description, int duration) {
		this.name = name;
		this.description = description;
		this.duration = duration;
	}
}

