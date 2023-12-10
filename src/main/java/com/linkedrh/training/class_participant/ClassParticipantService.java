package com.linkedrh.training.class_participant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;

@Service
public class ClassParticipantService {

	@Autowired
	ClassParticipantRepository repository;

	public List<ClassParticipant> list(int classCode) throws Exception {
		try {
			return this.repository.listByClass(classCode);
		} catch(Exception err) {
			throw err;
		}
	}

	public int create(ClassParticipantCreateDTO body) throws Exception {
		try {
			return this.repository.create(body);
		} catch (Exception err) {
			throw err;
		}
	}

	public void delete(int participantCode) throws Exception {
		this.repository.delete(participantCode);
	}
}

