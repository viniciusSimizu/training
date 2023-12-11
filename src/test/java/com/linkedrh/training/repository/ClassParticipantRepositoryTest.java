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
import com.linkedrh.training.class_.repository.IClassRepository;
import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.class_participant.repository.IClassParticipantRepository;
import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;
import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.repository.ICourseRepository;
import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.employee.repository.IEmployeeRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ClassParticipantRepositoryTest {

	private Class classTest;
	private List<Employee> employeesDb = new ArrayList<>();

	@Autowired
	private IClassParticipantRepository repository;

	@Autowired
	private ICourseRepository courseRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private IClassRepository classRepository;

	@BeforeAll
	public void setupDefaultData() throws SQLException {

		// Course
		String name = "Nome de teste";
		String description = "Descrição de teste";
		int duration = 100;

		CourseCreateDTO courseCreateBody = new CourseCreateDTO(name, description, duration);
		int courseCode = this.courseRepository.create(courseCreateBody);

		Course course = new Course(courseCode, duration, name, description);

		// Class
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "Indaiatuba";

		ClassCreateDTO createClass = new ClassCreateDTO(startDate, endDate, location, course.code());
		int classCode = this.classRepository.create(createClass);

		this.classTest = new Class(classCode, courseCode, startDate, endDate)
			.setLocation(location);

		// Employee
		this.employeesDb = this.employeeRepository.list();
	}

	@Test
	public void CreateClassParticipant_Test() throws SQLException {

		ClassParticipantCreateDTO createClassParticipant =
			new ClassParticipantCreateDTO(this.classTest.code(), this.employeesDb.get(0).code());
		int classParticipantCode = this.repository.create(createClassParticipant);

		ClassParticipant classParticipant = this.repository.findByCode(classParticipantCode);
		ClassParticipant expect =
			new ClassParticipant(classParticipantCode, classParticipant.classCode, classParticipant.employeeCode);

		Assertions.assertThat(classParticipant).isNotNull();
		Assertions.assertThat(classParticipant).isEqualTo(expect);
	}

	@Test
	public void DeleteClassParticipant_Test() throws SQLException {

		ClassParticipantCreateDTO createClassParticipant =
			new ClassParticipantCreateDTO(this.classTest.code(), this.employeesDb.get(0).code());
		int classParticipantCode = this.repository.create(createClassParticipant);

		this.repository.delete(classParticipantCode);

		ClassParticipant classParticipant = this.repository.findByCode(classParticipantCode);

		Assertions.assertThat(classParticipant).isNull();
	}

	@Test
	public void ListByClass_Test() throws SQLException {

		ClassParticipantCreateDTO createClassParticipant =
			new ClassParticipantCreateDTO(this.classTest.code(), this.employeesDb.get(0).code());
		int classParticipantCode = this.repository.create(createClassParticipant);

		ClassParticipant classParticipantOne =
			new ClassParticipant(classParticipantCode, createClassParticipant.classCode, createClassParticipant.employeeCode);

		createClassParticipant =
			new ClassParticipantCreateDTO(this.classTest.code(), this.employeesDb.get(1).code());
		classParticipantCode = this.repository.create(createClassParticipant);

		ClassParticipant classParticipantTwo =
			new ClassParticipant(classParticipantCode, createClassParticipant.classCode, createClassParticipant.employeeCode);

		List<ClassParticipant> classParticipants = this.repository.listByClass(this.classTest.code());
		List<ClassParticipant> expect =
			new ArrayList<>(List.of(classParticipantOne, classParticipantTwo));

		Assertions.assertThat(classParticipants).containsAll(expect);
	}
}
