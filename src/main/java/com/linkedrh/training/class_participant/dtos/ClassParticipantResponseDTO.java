package com.linkedrh.training.class_participant.dtos;

import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.employee.dtos.EmployeeResponseDTO;

public class ClassParticipantResponseDTO {

	public int classCode, employeeCode;
	
	public EmployeeResponseDTO employee;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		ClassParticipantResponseDTO that = (ClassParticipantResponseDTO) obj;

		if (this.classCode != that.classCode) return false;
		if (this.employeeCode != that.employeeCode) return false;

		if (this.employee != null || that.employee != null) {
			if (this.employee == null || that.employee == null) return false;
			if (this.employee.equals(that.employee)) return false;
		}

		return true;
	}

	public static ClassParticipantResponseDTO buildFromEntity(ClassParticipant classParticipant) {
		ClassParticipantResponseDTO response = new ClassParticipantResponseDTO();

		response.classCode = classParticipant.classCode;
		response.employeeCode = classParticipant.employeeCode;
		response.employee = EmployeeResponseDTO.buildFromEntity(classParticipant.employee);

		return response;
	}
}

