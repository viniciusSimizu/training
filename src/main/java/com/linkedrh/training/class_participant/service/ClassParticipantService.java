package com.linkedrh.training.class_participant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;
import com.linkedrh.training.class_participant.dtos.ClassParticipantResponseDTO;
import com.linkedrh.training.class_participant.repository.IClassParticipantRepository;
import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.employee.repository.IEmployeeRepository;

@Service
public class ClassParticipantService implements IClassParticipantService {

	@Autowired
	private IClassParticipantRepository repository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public List<ClassParticipantResponseDTO> listClassParticipants(int classParticipantCode) throws Exception {
		List<ClassParticipant> classParticipants = this.repository.listByClass(classParticipantCode);

		for (ClassParticipant classParticipant : classParticipants) {
			Employee employee = this.employeeRepository.findById(classParticipant.employeeCode);

			classParticipant.employee = employee;
		}

		return classParticipants.stream()
			.map(ClassParticipantResponseDTO::buildFromEntity)
			.toList();
	}

	@Override
	public int createClassParticipant(ClassParticipantCreateDTO createClassParticipantBody) throws Exception {
		return this.repository.create(createClassParticipantBody);
	}

	@Override
	public void deleteClassParticipant(int classParticipantCode) throws Exception {
		this.repository.delete(classParticipantCode);
	}
}

