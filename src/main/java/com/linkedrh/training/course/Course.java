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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Course that = (Course) obj;

		if (this.code() != that.code()) return false;
		if (this.duration() != that.duration()) return false;
		if (!this.name().equals(that.name())) return false;
		if (!this.description().equals(that.description())) return false;

		return true;
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
