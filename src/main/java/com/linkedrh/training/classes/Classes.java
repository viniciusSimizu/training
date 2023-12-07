package com.linkedrh.training.classes;

import java.time.LocalDate;

import com.linkedrh.training.course.Course;

public class Classes {
	private int code, courseCode;
	private LocalDate start, end;
	private String location;

	private Course course;

	public Classes (int code, int courseCode, LocalDate start, LocalDate end, String location) {
		this.code = code;
		this.courseCode = courseCode;
		this.start = start;
		this.end = end;
		this.location = location;
	}

	public int code() {
		return this.code;
	}
	public LocalDate start() {
		return this.start;
	}
	public Classes setStart(LocalDate start) {
		this.start = start;
		return this;
	}
	public LocalDate end() {
		return this.end;
	}
	public Classes setEnd(LocalDate end) {
		this.end = end;
		return this;
	}
	public String location() {
		return this.location;
	}
	public Classes setLocation(String location) {
		this.location = location;
		return this;
	}
	public int courseCode() {
		return this.courseCode;
	}

	public Course course() {
		return this.course;
	}
	public Classes setCourse(Course course) {
		this.course = course;
		return this;
	}
}

