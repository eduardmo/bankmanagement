package com.assig1.business.domainModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@Column
	private long accountNumber;
	@Column
	private String accountType;
	@Column
	private float amountOfMoney;
	@Column
	private Date creationDate;
	@Column
	private long ownerPNC;
	
//	
//	public int getAccountId() {
//		return accountId;
//	}
//
//	public void setAccountId(int accountId) {
//		this.accountId = accountId;
//	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getOwnerPNC() {
		return ownerPNC;
	}

	public void setOwnerPNC(long ownerPNC) {
		this.ownerPNC = ownerPNC;
	}
	
}
