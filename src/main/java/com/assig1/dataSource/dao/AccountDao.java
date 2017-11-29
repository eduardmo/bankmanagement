package com.assig1.dataSource.dao;

import java.util.List;

import com.assig1.business.domainModel.Account;

public interface AccountDao {
	public void add(Account account);
	public void edit(Account account);
	public Account get(long accountNumber);
	public void delete(Account account);
	public List<Account> getAllAccounts();
	public List<Account> getAllAccountsByPNC(long pnc);
	Account getAccountByNumber(long accountNumber);
}
