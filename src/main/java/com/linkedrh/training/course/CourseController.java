package com.linkedrh.training.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService service;

	@GetMapping
	public ResponseEntity<Object> list() {
		try {
			List<Course> courses = this.service.list();
			return ResponseEntity.ok(courses);
		} catch (Exception err) {
			return ResponseEntity
				.internalServerError()
				.body(err.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody CourseCreateDTO body) {
		try {
			int courseCode = this.service.create(body);

			Map<String, Object> response = new HashMap<>();
			response.put("courseCode", courseCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{code}")
	public ResponseEntity<Object> update(@PathVariable int code, @RequestBody CourseUpdateDTO body) {
		try {
			this.service.update(code, body);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Object> delete(@PathVariable int code) {
		try {
			this.service.delete(code);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
