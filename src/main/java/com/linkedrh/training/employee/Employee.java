package com.linkedrh.training.employee;

import java.time.LocalDate;

public class Employee {
	private int code;
	private String name, cpf, role;
	private LocalDate birthDay, admission;
	private boolean status;

	public Employee(int code, String name, String cpf, String role, LocalDate birthDay, LocalDate admission, boolean status) {
		this.code = code;
		this.name = name;
		this.cpf = cpf;
		this.birthDay = birthDay;
		this.role = role;
		this.admission = admission;
		this.status = status;
	}

	public int code() {
		return code;
	}
	public String name() {
		return name;
	}
	public String cpf() {
		return cpf;
	}
	public LocalDate birthDay() {
		return birthDay;
	}
	public String role() {
		return role;
	}
	public LocalDate admission() {
		return admission;
	}
	public boolean status() {
		return status;
	}
}
