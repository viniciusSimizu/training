package com.linkedrh.training.class_.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassResponseDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;
import com.linkedrh.training.class_.service.IClassService;
import com.linkedrh.training.shared.lib.ErrorResponseMessage;
import com.linkedrh.training.shared.lib.SuccessResponseMessage;

@RestController
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private IClassService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody ClassCreateDTO createClassBody) {

		try {
			int classCode = this.service.createClass(createClassBody);

			SuccessResponseMessage response = new SuccessResponseMessage();
			response.setAttribute("classCode", classCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response.build());
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível realizar a criação da turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(message));
		}
	}

	@PutMapping("/{classCode}")
	public ResponseEntity<Object> update(@PathVariable int classCode, @RequestBody ClassUpdateDTO updateClassBody) {

		try {
			this.service.updateClass(classCode, updateClassBody);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível realizar a atualização da turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(message));
		}
	}

	@DeleteMapping("/{classCode}")
	public ResponseEntity<Object> delete(@PathVariable int classCode) {

		try {
			this.service.deleteClass(classCode);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível realizar a deleção da turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(message));
		}
	}

	@GetMapping("/{courseCode}")
	public ResponseEntity<Object> listByCourse(@PathVariable int courseCode) {

		try {
			List<ClassResponseDTO> classes = this.service.listClassesByCourse(courseCode);
			return ResponseEntity.status(HttpStatus.OK).body(classes);
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível listar as turmas do curso, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(message));
		}
	}
}
