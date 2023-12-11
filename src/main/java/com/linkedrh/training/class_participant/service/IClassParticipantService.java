package com.linkedrh.training.class_participant.service;

import java.util.List;

import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;
import com.linkedrh.training.class_participant.dtos.ClassParticipantResponseDTO;

public interface IClassParticipantService {
	public List<ClassParticipantResponseDTO> listClassParticipants(int classParticipantCode) throws Exception;
	public int createClassParticipant(ClassParticipantCreateDTO createClassParticipantBody) throws Exception;
	public void deleteClassParticipant(int classParticipantCode) throws Exception;
}

