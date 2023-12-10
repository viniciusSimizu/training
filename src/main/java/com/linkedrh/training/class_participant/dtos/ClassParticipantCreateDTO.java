package com.linkedrh.training.class_participant.dtos;

public class ClassParticipantCreateDTO {

	public int classCode, employeeCode;

	public ClassParticipantCreateDTO(int classCode, int employeeCode) {
		this.classCode = classCode;
		this.employeeCode = employeeCode;
	}
}
