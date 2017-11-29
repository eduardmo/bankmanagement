package com.assig1.dataSource.dao;

import java.util.List;

import com.assig1.business.domainModel.Employee;

public interface EmployeeDao {
	public void add(Employee employee);
	public void edit(Employee employee);
	public Employee getEmployeeByPNC(long employeePNC);
	public Employee getEmployeeByUserName(String username);
	public void delete(Employee employee);
	public List<Employee> getAllEmployees();
}
