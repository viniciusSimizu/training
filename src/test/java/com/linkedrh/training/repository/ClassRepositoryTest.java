package com.linkedrh.training.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.class_.ClassRepository;
import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;
import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.CourseRepository;
import com.linkedrh.training.course.dtos.CourseCreateDTO;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ClassRepositoryTest {

	private Course courseTest;

	@Autowired
	private ClassRepository repository;

	@Autowired
	private CourseRepository courseRepository;

	@BeforeAll
	public void setupDefaultData() throws SQLException {
		String name = "Nome de teste";
		String description = "Descrição de teste";
		int duration = 100;

		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.courseRepository.create(courseCreateBody);

		this.courseTest = new Course(courseCode, duration, name, description);
	}

	@Test
	public void CreateClass_Test() throws SQLException {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "Indaiatuba";
		ClassCreateDTO createClass = new ClassCreateDTO(startDate, endDate, location, this.courseTest.code());

		int classCode = this.repository.create(createClass);

		Class class_ = this.repository.findByCode(classCode);
		Class expect = new Class(classCode, this.courseTest.code(), startDate, endDate)
			.setLocation(location);

		Assertions.assertThat(class_).isNotNull();
		Assertions.assertThat(class_).isEqualTo(expect);
	}

	@Test
	public void UpdateClass_Test() throws SQLException {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "São Paulo";
		ClassCreateDTO createClass = new ClassCreateDTO(startDate, endDate, location, this.courseTest.code());

		int classCode = this.repository.create(createClass);

		startDate = endDate;
		endDate = startDate.plus(1, ChronoUnit.DAYS);
		location = "Indaiatuba";

		ClassUpdateDTO classUpdateBody = new ClassUpdateDTO(startDate, endDate, location);
		this.repository.update(classCode, classUpdateBody);

		Class class_ = this.repository.findByCode(classCode);
		Class expect = new Class(classCode, this.courseTest.code(), startDate, endDate)
			.setLocation(location);

		Assertions.assertThat(class_).isNotNull();
		Assertions.assertThat(class_).isEqualTo(expect);
	}

	@Test
	public void DeleteClass_Test() throws SQLException {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "Indaiatuba";
		ClassCreateDTO createClass = new ClassCreateDTO(startDate, endDate, location, this.courseTest.code());

		int classCode = this.repository.create(createClass);
		this.repository.delete(classCode);

		Class course = this.repository.findByCode(classCode);

		Assertions.assertThat(course).isNull();
	}

	@Test
	public void ListByCourseCode_Test() throws SQLException {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "Indaiatuba";
		ClassCreateDTO createClassBody = new ClassCreateDTO(startDate, endDate, location, this.courseTest.code());

		int classCode = this.repository.create(createClassBody);

		Class classOne = new Class(classCode, this.courseTest.code(), startDate, endDate)
			.setLocation(location);

		startDate = LocalDate.from(endDate);
		endDate = startDate.plus(1, ChronoUnit.DAYS);
		location = "Indaiatuba";
		createClassBody = new ClassCreateDTO(startDate, endDate, location, this.courseTest.code());
		classCode = this.repository.create(createClassBody);

		Class classTwo = new Class(classCode, this.courseTest.code(), startDate, endDate)
			.setLocation(location);

		List<Class> courses = this.repository.listByCourseCode(this.courseTest.code());
		List<Class> created = new ArrayList<>(List.of(classOne, classTwo));

		Assertions.assertThat(courses).containsAll(created);
	}
}
