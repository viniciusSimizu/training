package com.linkedrh.training.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

@Service
public class CourseService {

	@Autowired
	CourseRepository repository;

	public List<Course> list() throws Exception {
		try {
			return this.repository.list();
		} catch(Exception err) {

			throw new Exception("Não foi possível listar os cursos disponíveis");
		}
	}

	public int create(CourseCreateDTO body) throws Exception {
		try {
			return this.repository.create(body);
		} catch (Exception err) {
			err.printStackTrace();
			throw new Exception("Não foi possível criar o curso");
		}
	}

	public void update(int courseCode, CourseUpdateDTO body) throws Exception {
		this.repository.update(courseCode, body);
	}

	public void delete(int courseCode) throws Exception {
		this.repository.delete(courseCode);
	}
}
