package com.assig1.dataSource.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assig1.business.domainModel.Employee;
import com.assig1.business.domainModel.Role;
import com.assig1.dataSource.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessF;
	@Override
	public void add(Employee employee) {
		Role r = sessF.getCurrentSession().get(Role.class, employee.getEmployeeRoleId());
		r.getEmpl().add(employee);
		employee.setEmployeeRole(r);
		sessF.getCurrentSession().save(employee);
	}
	@Override
	public void edit(Employee employee) {
//		Role r = new Role();
//		r.setRoleId(employee.getEmployeeRole().getRoleId());
		Role r = sessF.getCurrentSession().get(Role.class, employee.getEmployeeRoleId());
		r.getEmpl().add(employee);
		employee.setEmployeeRole(r);
		if(employee.getPassword().equals("")){
		Employee empl = sessF.getCurrentSession().get(Employee.class, employee.getEmployeePersonalNumericalCode());
		System.out.println(empl.getEmployeeName());
		employee.setPassword(empl.getPassword());
		}
		sessF.getCurrentSession().merge(employee);
	}

	@Override
	public Employee getEmployeeByPNC(long employeePNC) {
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Employee.class);
		crit.add(Restrictions.like("employeePersonalNumericalCode", employeePNC));
		return (Employee) crit.uniqueResult();
	}

	@Override
	public void delete(Employee employee) {
		sessF.getCurrentSession().delete(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Employee.class);
		List<Employee> list = (List<Employee>)crit.list();
		
		return list;
	}

	@Override
	public Employee getEmployeeByUserName(String username) {
		Criteria crit= sessF.getCurrentSession().createCriteria(Employee.class);
		crit.add(Restrictions.eq("username",username));
		return (Employee) crit.uniqueResult();
	}

}
