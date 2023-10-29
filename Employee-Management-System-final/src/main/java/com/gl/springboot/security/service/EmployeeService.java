package com.gl.springboot.security.service;

import java.util.List;

import com.gl.springboot.security.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);
	List<Employee> getAllEmployeeSortedByName(String order);
	List<Employee> findByKeyword(String keyword);
}
