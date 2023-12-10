package com.linkedrh.training.class_;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;

@Service
public class ClassService {

	@Autowired
	private ClassRepository repository;

	public int create(ClassCreateDTO body) throws Exception {
		return this.repository.create(body);
	}

	public void update(int code, ClassUpdateDTO body) throws Exception {
		this.repository.update(code, body);
	}

	public void delete(int code) throws Exception {
		this.repository.delete(code);
	}

	public List<Class> listByCourse(int courseCode) throws Exception {
		return this.repository.listByCourseCode(courseCode);
	}
}
