package com.linkedrh.training.course;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	CourseService service;

	@GetMapping
	public ResponseEntity<List<Course>> list() {
		try {
			List<Course> courses = this.service.list();
			return ResponseEntity.ok(courses);
		} catch (SQLException err) {
			return ResponseEntity.internalServerError().body(body);
		}
	}
}
