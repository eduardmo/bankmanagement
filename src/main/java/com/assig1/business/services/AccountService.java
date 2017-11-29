package com.assig1.business.services;

import java.util.List;

import com.assig1.business.domainModel.Account;

public interface AccountService {
	public void add(Account account);
	public void edit(Account account);
	public Account get(long l);
	public void delete(Account account);
	public List<Account> getAllAccounts();
	public List<Account> getAllAccountsByPNC(long pnc);
	String transferMoney(long accNo1, long accNo2, float ammount);
	public String withdrawMoney(long accountNumber, float amount);
	public String depositMoney(long accountNumber, float amount);
}
