package com.assig1.business.services;

import java.util.List;

import com.assig1.business.domainModel.EmployeeActivities;

public interface EmployeeActionsService {

	public void add(EmployeeActivities employeeActivity);
	public List<EmployeeActivities> get(long employeeActivityPersonalNumericalCode);
	public void delete(EmployeeActivities employeeActivity);

}
