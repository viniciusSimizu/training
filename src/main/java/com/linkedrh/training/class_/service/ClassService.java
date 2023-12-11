package com.linkedrh.training.class_.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassResponseDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;
import com.linkedrh.training.class_.repository.IClassRepository;
import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.class_participant.repository.IClassParticipantRepository;
import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.employee.repository.IEmployeeRepository;

@Service
public class ClassService implements IClassService {

	@Autowired
	private IClassRepository repository;

	@Autowired
	private IClassParticipantRepository classParticipantRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public int createClass(ClassCreateDTO createClassBody) throws Exception {
		return this.repository.create(createClassBody);
	}

	@Override
	public void updateClass(int classCode, ClassUpdateDTO updateClassBody) throws Exception {
		this.repository.update(classCode, updateClassBody);
	}

	@Override
	public void deleteClass(int classCode) throws Exception {
		this.repository.delete(classCode);
	}

	@Override
	public List<ClassResponseDTO> listClassesByCourse(int courseCode) throws Exception {
		List<Class> classes = this.repository.listByCourseCode(courseCode);

		for (Class class_ : classes) {
			List<ClassParticipant> participants =
				this.classParticipantRepository.listByClass(class_.code());

			for (ClassParticipant participant : participants) {
				Employee employee = this.employeeRepository.findById(participant.employeeCode);
				participant.employee = employee;
			}

			class_.setParticipants(participants);
		}

		return classes.stream()
			.map(ClassResponseDTO::buildFromEntity)
			.toList();
	}
}
