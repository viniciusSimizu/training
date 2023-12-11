package com.linkedrh.training.employee.dtos;

import java.time.LocalDate;

import com.linkedrh.training.employee.Employee;

public class EmployeeResponseDTO {

	public int code;
	public String name, cpf, role;
	public LocalDate birthDay, admission;
	public boolean status;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		EmployeeResponseDTO that = (EmployeeResponseDTO) obj;

		if (this.code != that.code) return false;
		if (!this.name.equals(that.name)) return false;
		if (!this.cpf.equals(that.cpf)) return false;
		if (!this.role.equals(that.role)) return false;
		if (!this.birthDay.isEqual(that.birthDay)) return false;
		if (!this.admission.isEqual(that.admission)) return false;
		if (this.status != that.status) return false;

		return true;
	}

	public static EmployeeResponseDTO buildFromEntity(Employee employee) {
		EmployeeResponseDTO response = new EmployeeResponseDTO();

		response.code = employee.code();
		response.name = employee.name();
		response.cpf = employee.cpf();
		response.role = employee.role();
		response.birthDay = employee.birthDay();
		response.admission = employee.admission();
		response.status = employee.status();

		return response;
	}
}
