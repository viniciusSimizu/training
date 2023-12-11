package com.linkedrh.training.class_participant.repository;

import java.sql.SQLException;
import java.util.List;

import com.linkedrh.training.class_participant.ClassParticipant;
import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;

public interface IClassParticipantRepository {

	public int create(ClassParticipantCreateDTO classParticipant) throws SQLException;
	public void delete(int code) throws SQLException;
	public List<ClassParticipant> listByClass(int classCode) throws SQLException;
	public ClassParticipant findByCode(int classParticipantCode) throws SQLException;
}
