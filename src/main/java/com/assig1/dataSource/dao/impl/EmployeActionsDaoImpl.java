package com.assig1.dataSource.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assig1.business.domainModel.Employee;
import com.assig1.business.domainModel.EmployeeActivities;
import com.assig1.dataSource.dao.EmployeeActionsDao;

@Repository
public class EmployeActionsDaoImpl implements EmployeeActionsDao {
	@Autowired
	private SessionFactory sessF;
	
	@Override
	public void add(EmployeeActivities employeeActivity) {
			sessF.getCurrentSession().save(employeeActivity);
	}

	@Override
	public List<EmployeeActivities> get(long employeeActivityPersonalNumericalCode) {
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(EmployeeActivities.class);
		List<EmployeeActivities> list = crit.add(Restrictions.like("employeeActivityPersonalNumericalCode",employeeActivityPersonalNumericalCode)).list();
		return list;
	}

	@Override
	public void delete(EmployeeActivities employeeActivity) {
		sessF.getCurrentSession().delete(employeeActivity);
	}

}
