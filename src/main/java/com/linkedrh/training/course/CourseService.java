package com.linkedrh.training.course;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.course.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository repository;

	public List<Course> list() throws SQLException {
		return this.repository.list();
	}
}
