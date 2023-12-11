package com.linkedrh.training.class_.service;

import java.util.List;

import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassResponseDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;

public interface IClassService {
	public int createClass(ClassCreateDTO createClassBody) throws Exception;
	public void updateClass(int classCode, ClassUpdateDTO updateClassBody) throws Exception;
	public void deleteClass(int classCode) throws Exception;
	public List<ClassResponseDTO> listClassesByCourse(int courseCode) throws Exception;
}
