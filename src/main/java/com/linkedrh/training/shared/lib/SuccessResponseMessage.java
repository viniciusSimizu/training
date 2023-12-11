package com.linkedrh.training.shared.lib;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponseMessage {

	private Map<String, Object> response = new HashMap<>();

	public SuccessResponseMessage setAttribute(String key, Object value) {
		this.response.put(key, value);
		return this;
	}

	public Map<String, Object> build() {
		return this.response;
	}
}
