package com.assig1.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assig1.business.domainModel.EmployeeActivities;
import com.assig1.business.services.EmployeeActionsService;
import com.assig1.dataSource.dao.EmployeeActionsDao;

@Service
public class EmployeeActionsServiceImpl implements EmployeeActionsService {
	@Autowired
	EmployeeActionsDao empActD;
	@Transactional
	public void add(EmployeeActivities employeeActivity) {
		empActD.add(employeeActivity);
	}
	@Transactional
	public List<EmployeeActivities> get(long employeeActivityPersonalNumericalCode) {
		return empActD.get(employeeActivityPersonalNumericalCode);
	}
	@Transactional
	public void delete(EmployeeActivities employeeActivity) {
		empActD.delete(employeeActivity);
	}

}
