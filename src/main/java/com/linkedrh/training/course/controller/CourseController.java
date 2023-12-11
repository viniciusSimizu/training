package com.linkedrh.training.course.controller;

import java.util.List;

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
import com.linkedrh.training.course.dtos.CourseResponseDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;
import com.linkedrh.training.course.service.ICourseService;
import com.linkedrh.training.shared.lib.ErrorResponseMessage;
import com.linkedrh.training.shared.lib.SuccessResponseMessage;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private ICourseService service;

	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> list() {

		try {
			List<CourseResponseDTO> courses = this.service.listCourses();

			SuccessResponseMessage response = new SuccessResponseMessage();
			response.setAttribute("courses", courses);

			return ResponseEntity.ok(response.build());
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message =  """
				Não foi possível listar os cursos disponíveis, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> create(@RequestBody CourseCreateDTO createCourseBody) {

		try {
			int courseCode = this.service.createCourse(createCourseBody);

			SuccessResponseMessage response = new SuccessResponseMessage();
			response.setAttribute("courseCode", courseCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response.build());
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message =  """
				Não foi possível realizar a criação do curso, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}

	@PutMapping("/{courseCode}")
	public ResponseEntity<Object> update(@PathVariable int courseCode, @RequestBody CourseUpdateDTO updateCourseBody) {

		try {
			this.service.updateCourse(courseCode, updateCourseBody);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message =  """
				Não foi possível realizar a atualização do curso, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}

	@DeleteMapping("/{courseCode}")
	public ResponseEntity<Object> delete(@PathVariable int courseCode) {

		try {
			this.service.deleteCourse(courseCode);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message =  """
				Não foi possível realizar a deleção curso, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}
}
