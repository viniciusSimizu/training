package com.linkedrh.training.class_participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("/class/participant")
public class ClassParticipantController {

	@Autowired
	ClassParticipantService service;

	@GetMapping("/{classCode}")
	public ResponseEntity<Object> list(@PathVariable int classCode) {
		try {
			List<ClassParticipant> courses = this.service.list(classCode);
			return ResponseEntity.ok(courses);
		} catch (Exception err) {

			return ResponseEntity
				.internalServerError()
				.body(err.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody ClassParticipantCreateDTO body) {
		try {
			int classParticipantCode = this.service.create(body);

			Map<String, Object> response = new HashMap<>();
			response.put("classParticipantCode", classParticipantCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Object> delete(@PathVariable int code) {
		try {
			this.service.delete(code);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}
}

