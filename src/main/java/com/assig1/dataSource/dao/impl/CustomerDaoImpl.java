package com.assig1.dataSource.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assig1.business.domainModel.Customer;
import com.assig1.dataSource.dao.CustomerDao;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessF;
	
	@Override
	public void add(Customer customer) {
		sessF.getCurrentSession().save(customer);
	}

	@Override
	public void edit(Customer customer) {
		
		sessF.getCurrentSession().update(customer);
	}

	@Override
	public Customer get(long customerPersonalNumericalCode) {
		return (Customer)sessF.getCurrentSession().get(Customer.class, customerPersonalNumericalCode);
	}

	@Override
	public List<Customer> getAllCustomers() { 
	    sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Customer.class);
		List<Customer> list = (List<Customer>)crit.list();
	    return list;
	}
}
