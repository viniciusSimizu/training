package com.linkedrh.training.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassResponseDTO;
import com.linkedrh.training.class_.repository.IClassRepository;
import com.linkedrh.training.class_.service.ClassService;
import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.class_participant.repository.IClassParticipantRepository;
import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.employee.repository.IEmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class ClassServiceTest {

	@Mock
	private IClassRepository repository;

	@Mock
	private IClassParticipantRepository classParticipantRepository;

	@Mock
	private IEmployeeRepository employeeRepository;

	@InjectMocks
	private ClassService service;

	@Test
	public void CreateClass_Test() throws Exception {
		int code = 10;

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "location";
		int courseCode = 10;
		ClassCreateDTO createClassBody = new ClassCreateDTO(startDate, endDate, location, courseCode);

		Mockito.when(this.repository.create(createClassBody)).thenReturn(code);

		int result = this.service.createClass(createClassBody);

		assert result == code;
	}

	@Test
	public void ListClassesByCourse_Test() throws Exception {
		int classCode = 10;
		int courseCode = 1;
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plus(1, ChronoUnit.DAYS);
		String location = "location";

		Class class_ = new Class(classCode, courseCode, startDate, endDate)
			.setLocation(location);

		int employeeCode = 15;
		Employee employee = new Employee(employeeCode, "n", "n", "n", LocalDate.now(), LocalDate.now(), true);

		int participantCode = 1;
		ClassParticipant participant = new ClassParticipant(participantCode, classCode, employeeCode);

		Mockito.when(this.repository.listByCourseCode(courseCode))
			.thenReturn(List.of(class_));

		Mockito.when(this.classParticipantRepository.listByClass(classCode))
			.thenReturn(List.of(participant));

		Mockito.when(this.employeeRepository.findById(employeeCode)).thenReturn(employee);

		List<ClassResponseDTO> result = this.service.listClassesByCourse(courseCode);

		participant.employee = employee;
		class_.setParticipants(List.of(participant));

		ClassResponseDTO expect = ClassResponseDTO.buildFromEntity(class_);

		Assertions.assertThat(result).containsExactly(expect);
	}
}
