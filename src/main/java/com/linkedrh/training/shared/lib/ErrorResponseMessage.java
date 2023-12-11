package com.linkedrh.training.shared.lib;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseMessage {

	private Map<String, Object> response = new HashMap<>();

	public Map<String, Object> message(Exception err, String message) {
		this.response.put("message", message);
		this.response.put("error", err.getMessage());

		return this.response;
	}

	public Map<String, Object> message(String message) {
		this.response.put("message", message);

		return this.response;
	}
}

