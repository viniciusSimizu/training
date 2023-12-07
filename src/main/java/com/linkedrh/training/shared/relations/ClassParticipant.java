package com.linkedrh.training.shared.relations;

import com.linkedrh.training.classes.Classes;
import com.linkedrh.training.employee.Employee;

public class ClassParticipant {
	private int code, classesCode, employeeCode;

	private Classes classes;
	private Employee employee;

	public ClassParticipant(int code, int classesCode, int employeeCode) {
		this.code = code;
		this.classesCode = classesCode;
		this.employeeCode = employeeCode;
	}

	public int code() {
		return code;
	}
	public int classesCode() {
		return classesCode;
	}
	public int employeeCode() {
		return employeeCode;
	}

	public Classes classes() {
		return classes;
	}
	public ClassParticipant setClasses(Classes classes) {
		this.classes = classes;
		return this;
	}
	public Employee employee() {
		return employee;
	}
	public ClassParticipant setEmployee(Employee employee) {
		this.employee = employee;
		return this;
	}
}
