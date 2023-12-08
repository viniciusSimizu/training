package com.linkedrh.training.course;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

@Service
public class CourseService {

	@Autowired
	CourseRepository repository;

	public List<Course> list() throws SQLException {
		return this.repository.list();
	}

	public void create(CourseCreateDTO body) throws SQLException {
		this.repository.create(body);
	}

	public void update(int courseCode, CourseUpdateDTO body) throws Exception {
		try {
			this.repository.update(courseCode, body);
		} catch (SQLException sqlError) {
			throw new Exception("Error on updating");
		}
	}

	public void delete(int courseCode) throws Exception {
		try {
			this.repository.delete(courseCode);
		} catch (SQLException sqlError) {
			throw new Exception("Error on deleting");
		}
	}
}
