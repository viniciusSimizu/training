package com.linkedrh.training.shared.lib;

import java.sql.SQLException;

public class RepositoryException extends Exception {
	public RepositoryException(SQLException error, String info) {
		super(RepositoryException.messageFormat(error, info));
	}

	private static String messageFormat(SQLException error, String info) {
		StringBuilder message = new StringBuilder();

		message.append("["+error.getErrorCode()+"]: ");
		message.append(info + "\n");
		message.append(error.getMessage());

		return message.toString();
	}
}
