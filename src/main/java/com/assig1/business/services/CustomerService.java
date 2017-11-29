package com.assig1.business.services;

import java.util.List;

import com.assig1.business.domainModel.Customer;

public interface CustomerService {
	public void add(Customer customer);
	public void edit(Customer customer);
	public Customer get(long customerPersonalNumericalCode);
	public List<Customer> getAllCustomers();
}
