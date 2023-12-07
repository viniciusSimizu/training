package com.linkedrh.training.course;

public class Course {
	private int code, duration;
	private String name, description;

	public Course(int code, int duration, String name, String description) {
		this.code = code;
		this.duration = duration;
		this.name = name;
		this.description = description;
	}

	public int code() {
		return this.code;
	}
	public String name() {
		return this.name;
	}
	public Course setName(String name) {
		this.name = name;
		return this;
	}
	public String description() {
		return this.description;
	}
	public Course setDescription(String description) {
		this.description = description;
		return this;
	}
	public int duration() {
		return this.duration;
	}
	public Course setDuration(int duration) {
		this.duration = duration;
		return this;
	}
}
