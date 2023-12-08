package com.linkedrh.training.course.dtos;

public class CourseCreateDTO {
	public String name, description;
	public int duration;

	public CourseCreateDTO(String name, String description, int duration) {
		this.name = name;
		this.description = description;
		this.duration = duration;
	}
}
