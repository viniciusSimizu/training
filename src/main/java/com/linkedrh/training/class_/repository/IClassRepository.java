package com.linkedrh.training.class_.repository;

import java.sql.SQLException;
import java.util.List;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;

public interface IClassRepository {

	public int create(ClassCreateDTO class_) throws SQLException;
	public void update(int code, ClassUpdateDTO class_) throws SQLException;
	public void delete(int code) throws SQLException;
	public List<Class> listByCourseCode(int courseCode) throws SQLException;
	public Class findByCode(int classCode) throws SQLException;
}
