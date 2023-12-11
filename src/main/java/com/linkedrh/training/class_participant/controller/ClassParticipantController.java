package com.linkedrh.training.class_participant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;
import com.linkedrh.training.class_participant.dtos.ClassParticipantResponseDTO;
import com.linkedrh.training.class_participant.service.IClassParticipantService;
import com.linkedrh.training.shared.lib.ErrorResponseMessage;
import com.linkedrh.training.shared.lib.SuccessResponseMessage;

@RestController
@RequestMapping("/class/participant")
public class ClassParticipantController {

	@Autowired
	private IClassParticipantService service;

	@GetMapping("/{classCode}")
	public ResponseEntity<Object> list(@PathVariable int classCode) {

		try {
			List<ClassParticipantResponseDTO> classParticipants = this.service.listClassParticipants(classCode);

			SuccessResponseMessage response = new SuccessResponseMessage();
			response.setAttribute("classParticipants", classParticipants);

			return ResponseEntity.ok(response.build());
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível listar os participantes da turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody ClassParticipantCreateDTO createClassParticipantBody) {

		try {
			int classParticipantCode = this.service.createClassParticipant(createClassParticipantBody);

			SuccessResponseMessage response = new SuccessResponseMessage();
			response.setAttribute("classParticipantCode", classParticipantCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response.build());
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível registrar o participante na turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}

	@DeleteMapping("/{classParticipantCode}")
	public ResponseEntity<Object> delete(@PathVariable int classParticipantCode) {

		try {
			this.service.deleteClassParticipant(classParticipantCode);
			return ResponseEntity.ok().build();
		} catch (Exception err) {

			ErrorResponseMessage response = new ErrorResponseMessage();
			String message = """
				Não foi possível remover o participate da turma, 
				tente novamente mais tarde.
				""";

			return ResponseEntity
				.internalServerError()
				.body(response.message(err, message));
		}
	}
}

