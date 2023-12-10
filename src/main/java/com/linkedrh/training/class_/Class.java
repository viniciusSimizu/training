package com.linkedrh.training.class_;

import java.time.LocalDate;
import java.util.List;

import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.course.Course;

public class Class {
	private int code, courseCode;
	private LocalDate startDate, endDate;
	private String location = null;

	private Course course;
	private List<ClassParticipant> participants;

	public Class (int code, int courseCode, LocalDate startDate, LocalDate endDate) {
		this.code = code;
		this.courseCode = courseCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		Class that = (Class) obj;

		if (this.code() != that.code()) return false;
		if (this.courseCode() != that.courseCode()) return false;
		if (!this.startDate().isEqual(that.startDate())) return false;
		if (!this.endDate().isEqual(that.endDate())) return false;
		if (!this.location().equals(that.location())) return false;
		if (this.course() != that.course()) return false;

		if (this.participants() != null || that.participants() != null) {
			if (this.participants() == null || that.participants() == null) return false;
			if (!this.participants().equals(that.participants())) return false;
		}

		return true;
	}

	public int code() {
		return this.code;
	}
	public LocalDate startDate() {
		return this.startDate;
	}
	public Class setStartDate(LocalDate start) {
		this.startDate = start;
		return this;
	}
	public LocalDate endDate() {
		return this.endDate;
	}
	public Class setEndDate(LocalDate end) {
		this.endDate = end;
		return this;
	}
	public String location() {
		return this.location;
	}
	public Class setLocation(String location) {
		this.location = location;
		return this;
	}
	public int courseCode() {
		return this.courseCode;
	}

	public Course course() {
		return this.course;
	}
	public Class setCourse(Course course) {
		this.course = course;
		return this;
	}

	public List<ClassParticipant> participants() {
		return this.participants;
	}
	public Class setParticipants(List<ClassParticipant> participants) {
		this.participants = participants;
		return this;
	}
}

