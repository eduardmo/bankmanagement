package com.assig1.business.services;

import java.util.List;

import com.assig1.business.domainModel.Employee;

public interface EmployeeService {
	public void add(Employee employee);
	public void edit(Employee employee);
	public Employee getEmployeeByPNC(long l);
	public void delete(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeByUsername(String username);
}
