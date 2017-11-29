package com.assig1.dataSource.dao;

import java.util.List;

import com.assig1.business.domainModel.Customer;

public interface CustomerDao {
	public void add(Customer customer);
	public void edit(Customer customer);
	public Customer get(long customerPersonalNumericalCode);
	public List<Customer> getAllCustomers();
}