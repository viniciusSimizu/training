package com.linkedrh.training.course.dtos;

import com.linkedrh.training.course.Course;

public class CourseResponseDTO {
	public int code, duration;
	public String name, description;

	public CourseResponseDTO(int code, String name, String description, int duration) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.duration = duration;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		CourseResponseDTO that = (CourseResponseDTO) obj;

		if (this.code != that.code) return false;
		if (this.duration != that.duration) return false;
		if (!this.name.equals(that.name)) return false;
		if (!this.description.equals(that.description)) return false;

		return true;
	}

	public static CourseResponseDTO buildFromEntity(Course course) {
		return new CourseResponseDTO(
				course.code(),
				course.name(),
				course.description(),
				course.duration());
	}
}

