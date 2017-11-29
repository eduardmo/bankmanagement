package com.assig1.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assig1.business.domainModel.Customer;
import com.assig1.business.services.CustomerService;
import com.assig1.dataSource.dao.CustomerDao;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Transactional
	public void add(Customer customer) {
		customerDao.add(customer);
	}

	@Transactional
	public void edit(Customer customer) {
		customerDao.edit(customer);

	}

	@Transactional
	public Customer get(long customerPersonalNumericalCode) {
		return customerDao.get(customerPersonalNumericalCode);
	}

	@Transactional
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

}
