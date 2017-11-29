package com.assig1.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assig1.business.domainModel.Employee;
import com.assig1.business.services.EmployeeService;
import com.assig1.dataSource.dao.EmployeeDao;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao empD;
	
	@Transactional
	public void add(Employee employee) {
		empD.add(employee);
	}

	@Transactional
	public void edit(Employee employee) {
		empD.edit(employee);
	}

	@Transactional
	public Employee getEmployeeByPNC(long employeePNC) {
		return empD.getEmployeeByPNC(employeePNC);
	}

	@Transactional
	public void delete(Employee employee) {
		empD.delete(employee);
	}

	@Transactional
	public List<Employee> getAllEmployees() {
		return empD.getAllEmployees();
	}

	@Transactional
	public Employee getEmployeeByUsername(String username) {
		return empD.getEmployeeByUserName(username);
	}

}
