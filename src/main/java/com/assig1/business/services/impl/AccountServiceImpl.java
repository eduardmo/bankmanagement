package com.assig1.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assig1.business.domainModel.Account;
import com.assig1.business.services.AccountService;
import com.assig1.dataSource.dao.AccountDao;
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accD;
	
	@Transactional
	public void add(Account account) {
		accD.add(account);
	}

	@Transactional
	public void edit(Account account) {
		accD.edit(account);
	}

	@Transactional
	public Account get(long accountNumber) {
		return accD.get(accountNumber);
	}

	@Transactional
	public void delete(Account account) {
		accD.delete(account);
	}

	@Transactional
	public List<Account> getAllAccounts() {
		return accD.getAllAccounts();
	}

	@Transactional
	public List<Account> getAllAccountsByPNC(long pnc) {
		return accD.getAllAccountsByPNC(pnc);
	}
	
	@Transactional
	public String transferMoney(long accNo1, long accNo2, float ammount) {
		Account acc1 = accD.get(accNo1);
		Account acc2 = accD.get(accNo2);
		if(acc1 == null){
			return "acc1";
		}
		if(acc2 == null){
			return "acc2";
		}
		if(acc1.getAmountOfMoney() >= ammount){
			acc1.setAmountOfMoney(acc1.getAmountOfMoney() - ammount);
			acc2.setAmountOfMoney(acc2.getAmountOfMoney() + ammount);
			return "success";
		}
		else{
		return "fail";
		}
	}

	@Transactional
	public String withdrawMoney(long accountNumber, float amount) {
		Account acc1 = accD.get(accountNumber);
		if(acc1 == null){
			return "acc1";
		}
		if(acc1.getAmountOfMoney() >= amount){
			acc1.setAmountOfMoney(acc1.getAmountOfMoney() - amount);
			return "success";
		}
		else{
		return "fail";
		}
	}

	@Transactional
	public String depositMoney(long accountNumber, float amount) {
		Account acc1 = accD.get(accountNumber);
		if(acc1 == null){
			return "acc1";
		}
		acc1.setAmountOfMoney(acc1.getAmountOfMoney() + amount);
		return "success";
	}

}
