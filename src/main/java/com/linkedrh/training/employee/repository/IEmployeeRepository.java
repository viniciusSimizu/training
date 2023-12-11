package com.linkedrh.training.employee.repository;

import java.sql.SQLException;
import java.util.List;

import com.linkedrh.training.employee.Employee;

public interface IEmployeeRepository {

	public List<Employee> list() throws SQLException;
	public Employee findById(int employeeCode) throws SQLException;
}

