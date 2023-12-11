package com.linkedrh.training.course.repository;

import java.sql.SQLException;
import java.util.List;

import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

public interface ICourseRepository {

	public List<Course> list() throws SQLException;
	public int create(CourseCreateDTO course) throws SQLException;
	public void update(int code, CourseUpdateDTO course) throws SQLException;
	public void delete(int code) throws SQLException;
	public Course findByCode(int courseCode) throws SQLException;
}
