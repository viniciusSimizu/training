package com.linkedrh.training.class_participant;

import com.linkedrh.training.class_.Class;
import com.linkedrh.training.employee.Employee;

public class ClassParticipant {
	public int code, classCode, employeeCode;

	public Class class_;
	public Employee employee;

	public ClassParticipant(int code, int classCode, int employeeCode) {
		this.code = code;
		this.classCode = classCode;
		this.employeeCode = employeeCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		ClassParticipant that = (ClassParticipant) obj;

		if (this.code != that.code) return false;
		if (this.classCode != that.classCode) return false;
		if (this.employeeCode != that.employeeCode) return false;

		if (this.class_ != null || that.class_ != null) {
			if (this.class_ == null || that.class_ == null) return false;
			if (!this.class_.equals(that.class_)) return false;
		}

		if (this.employee != null || that.employee != null) {
			if (this.employee == null || that.employee == null) return false;
			if (!this.employee.equals(that.employee)) return false;
		}

		return true;
	}
}
