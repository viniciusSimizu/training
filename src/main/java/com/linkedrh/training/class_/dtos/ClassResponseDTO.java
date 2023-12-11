package com.linkedrh.training.class_.dtos;

import java.time.LocalDate;
import java.util.List;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.class_participant.dtos.ClassParticipantResponseDTO;

public class ClassResponseDTO {
	public int code, courseCode, participantsQuantity;
	public LocalDate startDate, endDate;
	public String location;

	public List<ClassParticipantResponseDTO> participants;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		ClassResponseDTO that = (ClassResponseDTO) obj;

		if (this.code != that.code) return false;
		if (this.courseCode != that.courseCode) return false;
		if (this.participantsQuantity != that.participantsQuantity) return false;
		if (!this.startDate.isEqual(that.startDate)) return false;
		if (!this.endDate.isEqual(that.endDate)) return false;
		if (!this.location.equals(that.location)) return false;

		if (this.participants != null || that.participants != null) {
			if (this.participants == null || that.participants == null) return false;
			if (this.participants.equals(that.participants)) return false;
		}

		return true;
	}

	public static ClassResponseDTO buildFromEntity(Class class_) {
		ClassResponseDTO response = new ClassResponseDTO();

		response.code = class_.code();
		response.courseCode = class_.courseCode();
		response.startDate = class_.startDate();
		response.endDate = class_.endDate();
		response.location = class_.location();
		response.participants = class_.participants()
			.stream()
			.map(ClassParticipantResponseDTO::buildFromEntity)
			.toList();
		response.participantsQuantity = response.participants.size();

		return response;
	}
}
