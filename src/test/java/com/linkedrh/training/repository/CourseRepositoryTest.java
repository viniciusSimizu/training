package com.linkedrh.training.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.CourseRepository;
import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository repository;

	@Test
	public void createCourse() throws SQLException {

		String name = "Nome de teste";
		String description = "Descrição de teste";
		int duration = 100;

		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.repository.create(courseCreateBody);

		Course course = this.repository.findByCode(courseCode);
		Course expect = new Course(courseCode, duration, name, description);

		Assertions.assertThat(course).isNotNull();
		Assertions.assertThat(course).isEqualTo(expect);
	}

	@Test
	public void updateCourse() throws SQLException {

		String name = "name before update";
		String description = "description before update";
		int duration = 0;

		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.repository.create(courseCreateBody);

		name = "name after update";
		description = "description after update";
		duration = 100;

		CourseUpdateDTO courseUpdateBody = new CourseUpdateDTO(name, description, duration);
		this.repository.update(courseCode, courseUpdateBody);

		Course course = this.repository.findByCode(courseCode);
		Course expect = new Course(courseCode, duration, name, description);

		Assertions.assertThat(course).isNotNull();
		Assertions.assertThat(course).isEqualTo(expect);
	}

	@Test
	public void deleteCourse() throws SQLException {

		String name = "name";
		String description = "description";
		int duration = 1;

		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.repository.create(courseCreateBody);
		this.repository.delete(courseCode);

		Course course = this.repository.findByCode(courseCode);

		Assertions.assertThat(course).isNull();
	}

	@Test
	public void listCourses() throws SQLException {

		String name = "name one";
		String description = "description one";
		int duration = 1;
		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.repository.create(courseCreateBody);

		Course courseOne = new Course(courseCode, duration, name, description);

		name = "name two";
		description = "description two";
		duration = 2;
		courseCreateBody = new CourseCreateDTO(name, description, duration);
		courseCode = this.repository.create(courseCreateBody);

		Course courseTwo = new Course(courseCode, duration, name, description);

		List<Course> courses = this.repository.list();
		List<Course> created = new ArrayList<>(List.of(courseOne, courseTwo));

		Assertions.assertThat(courses).containsAll(created);
	}
}
