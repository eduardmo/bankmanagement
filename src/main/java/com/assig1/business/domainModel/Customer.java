package com.assig1.business.domainModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@Column
	private long customerPersonalNumericalCode;
	@Column
	private String customerName;
	@Column
	private String customerIdCardNumber;
	@Column
	private String customerAddress;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
	}

	public String getCustomerIdCardNumber() {
		return customerIdCardNumber;
	}

	public void setCustomerIdCardNumber(String customerIdCardNumber) {
		this.customerIdCardNumber = customerIdCardNumber;
	}

	public long getCustomerPersonalNumericalCode() {
		return customerPersonalNumericalCode;
	}

	public void setCustomerPersonalNumericalCode(long customerPersonalNumericalCode) {
		this.customerPersonalNumericalCode = customerPersonalNumericalCode;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	

}
