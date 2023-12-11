package com.linkedrh.training.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseResponseDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;
import com.linkedrh.training.course.repository.ICourseRepository;
import com.linkedrh.training.course.service.CourseService;
import com.linkedrh.training.course.service.ICourseService;

public class CourseServiceTest {

	private ICourseRepository repository;
	private ICourseService service;

	@BeforeEach
	public void setup() {
		this.repository = new CourseRepositoryMock();
		this.service = new CourseService(this.repository);
	}

	@Test
	public void ListCourses_Test() throws Exception {
		String name = "name";
		String description = "description";
		int duration = 1;
		CourseCreateDTO course = new CourseCreateDTO(name, description, duration);

		int code = this.repository.create(course);

		List<CourseResponseDTO> courses = this.service.listCourses();
		CourseResponseDTO expect =
			new CourseResponseDTO(code, name, description, duration);

		Assertions.assertThat(courses).containsExactly(expect);
	}

	@Test
	public void CreateCourse_Test() throws Exception {
		String name = "name";
		String description = "description";
		int duration = 1;
		CourseCreateDTO createCourseBody = new CourseCreateDTO(name, description, duration);

		int code = this.service.createCourse(createCourseBody);

		Course expect = this.repository.findByCode(code);
		Course course = new Course(code, duration, name, description);

		Assertions.assertThat(course).isEqualTo(expect);
	}

	@Test
	public void UpdateCourse_Test() throws Exception {
		String name = "name";
		String description = "description";
		int duration = 1;
		CourseCreateDTO createCourseBody = new CourseCreateDTO(name, description, duration);
		int code = this.repository.create(createCourseBody);

		name += "updated";
		description += "updated";
		duration = 2;
		CourseUpdateDTO updateCourseBody = new CourseUpdateDTO(name, description, duration);

		this.service.updateCourse(code, updateCourseBody);

		Course result = this.repository.findByCode(code);
		Course expect = new Course(code, duration, name, description);

		Assertions.assertThat(result).isEqualTo(expect);
	}
}

class CourseRepositoryMock implements ICourseRepository {

	private Integer idSeq = 1;
	private Map<Integer, Course> dbCourses = new HashMap<>();

	@Override
	public List<Course> list() throws SQLException {
		return List.copyOf(this.dbCourses.values());
	}

	@Override
	public int create(CourseCreateDTO course) throws SQLException {
		Course newCourse = new Course(this.idSeq, course.duration, course.name, course.description);
		int id = this.idSeq;

		this.dbCourses.put(id, newCourse);
		this.idSeq++;

		return id;
	}

	@Override
	public void update(int code, CourseUpdateDTO course) throws SQLException {
		Course updateCourse = this.dbCourses.get(code);
		updateCourse.setName(course.name);
		updateCourse.setDuration(course.duration);
		updateCourse.setDescription(course.description);
	}

	@Override
	public void delete(int code) throws SQLException {
		this.dbCourses.remove(code);
	}

	@Override
	public Course findByCode(int courseCode) throws SQLException {
		return this.dbCourses.get(courseCode);
	}
}
