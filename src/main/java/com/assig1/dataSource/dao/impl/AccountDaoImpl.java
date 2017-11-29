package com.assig1.dataSource.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assig1.business.domainModel.Account;
import com.assig1.business.domainModel.Customer;
import com.assig1.business.domainModel.Employee;
import com.assig1.dataSource.dao.AccountDao;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private SessionFactory sessF;
	@Override
	public void add(Account account) {
		long accountNo = (long) (Math.random() * 10000000000000000L);
		while(this.get(accountNo)!=null){
			accountNo = (long) (Math.random() * 10000000000000000L);
		}
		account.setAccountNumber(accountNo);
		sessF.getCurrentSession().save(account);
	}

	@Override
	public void edit(Account account) {
			Account acc = sessF.getCurrentSession().get(Account.class, account.getAccountNumber());
			System.out.println(acc.getCreationDate());
			account.setCreationDate(acc.getCreationDate());
		System.out.println(account.getCreationDate());
		sessF.getCurrentSession().merge(account);
	}

	@Override
	public Account get(long accountNumber) {
		return sessF.getCurrentSession().get(Account.class, accountNumber);
	}

	@Override
	public void delete(Account account) {
		sessF.getCurrentSession().delete(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Account.class);
		List<Account> list = (List<Account>)crit.list();
		return list;
	}

	@Override
	public List<Account> getAllAccountsByPNC(long pnc) {
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Account.class);
		List<Account> list = crit.add(Restrictions.like("ownerPNC",pnc)).list();
		return list;
	}
	@Override
	public Account getAccountByNumber(long accountNumber){
		sessF.getCurrentSession().beginTransaction();
		Criteria crit = sessF.getCurrentSession().createCriteria(Account.class);
		Account acc = (Account) crit.add(Restrictions.like("accountNumber",accountNumber)).uniqueResult();
		return acc;
	}

}
