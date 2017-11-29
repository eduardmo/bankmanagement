package com.assig1.presentation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assig1.business.domainModel.Account;
import com.assig1.business.domainModel.EmployeeActivities;
import com.assig1.business.services.AccountService;
import com.assig1.business.services.CustomerService;
import com.assig1.business.services.EmployeeActionsService;
import com.assig1.business.services.EmployeeService;

@Controller("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeActionsService employeeActionsService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/account")
	public String setupForm(Map<String, Object> map) {
		map.put("showEdit", "false");
		map.put("account", new Account());
		map.put("accountList", accountService.getAllAccounts());
		return "account";
	}

	@RequestMapping(value = "account", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Account account, BindingResult result, @RequestParam String action,
			Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		Account accountResult = new Account();
		switch (action.toLowerCase()) {
		case "add":
			if (customerService.get(account.getOwnerPNC()) != null) {
				Date date = new Date();
				account.setCreationDate(date);
				accountService.add(account);
				accountResult = account;
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Added account with number: " + account.getAccountNumber()
						+ " for customer with PNC: " + account.getOwnerPNC());
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
			} else {
				map.put("showEdit", "false");
				map.put("message", "No customer with that PNC found!");
				map.put("accountList", accountService.getAllAccounts());
			}
			break;
		case "edit":
			accountService.edit(account);
			accountResult = account;
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity("Edited account with number: " + account.getAccountNumber()
					+ " for customer with PNC: " + account.getOwnerPNC());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			break;
		case "delete":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity("Deleted account with number: " + account.getAccountNumber()
					+ " for customer with PNC: " + account.getOwnerPNC());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			accountService.delete(account);
			accountResult = account;
			break;
		}
		map.put("account", accountResult);
		map.put("accountList", accountService.getAllAccounts());
		return "account";
	}

	@RequestMapping(value = "account.get", method = RequestMethod.GET, params = { "accountNumber", "action" })
	public String search(@RequestParam("accountNumber") long accountNumber, @RequestParam("action") String action,
			@ModelAttribute("account") Account account, BindingResult result, Map<String, Object> map) {

		switch (action.toLowerCase()) {
		case "search":
			Account searcherdAccount = accountService.get(accountNumber);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername(); // get logged in username
			EmployeeActivities employeeActivity = new EmployeeActivities();
			long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
			if (searcherdAccount != null) {
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Searched for account number: " + account.getAccountNumber());
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
				map.put("accountl", searcherdAccount);
				map.put("showEdit", "true");
				break;
			} else {
				map.put("showEdit", "false");
				map.put("message", "Account not found!");
				map.put("accountList", accountService.getAllAccounts());
				break;
			}
		case "show all":
			map.put("showEdit", "false");
			map.put("accountList", accountService.getAllAccounts());
			break;
		}
		Account accountResult = new Account();
		map.put("account", accountResult);
		return "account";
	}

	@RequestMapping(value = "account.getAll", method = RequestMethod.GET, params = { "ownerPNC", "action" })
	public String searchAll(@RequestParam("ownerPNC") long ownerPNC, @RequestParam("action") String action,
			@ModelAttribute("account") Account account, BindingResult result, Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		List<Account> list = accountService.getAllAccountsByPNC(ownerPNC);
		if (!list.isEmpty()) {
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity("Searched accounts for customer with PNC: " + ownerPNC);
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			map.put("showEdit", "false");
			map.put("accountList", list);
		} else {
			map.put("showEdit", "false");
			map.put("messagePNC", "No accounts found for that PNC!");
			map.put("accountList", accountService.getAllAccounts());
		}
		Account accountResult = new Account();
		map.put("account", accountResult);
		return "account";
	}

	@RequestMapping(value = "account.transfer", method = RequestMethod.GET, params = { "accountToTransfer", "amount",
			"action" })
	public String transfer(@RequestParam("amount") float amount,
			@RequestParam("accountToTransfer") long accountToTransfer, @RequestParam("action") String action,
			@ModelAttribute("account") Account account, BindingResult result, Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		String succ = accountService.transferMoney(account.getAccountNumber(), accountToTransfer, (float) amount);
		System.out.println(succ);
		switch (succ) {
		case "success":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity(
					"Transfered from account: " + account.getAccountNumber() + "to: " + accountToTransfer);
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			map.put("showEdit", "false");
			map.put("message1", "Successfully Transfered");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "fail":
			map.put("showEdit", "false");
			map.put("message2", "Insufficient Funds");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "acc1":
			map.put("showEdit", "false");
			map.put("message2", "Account 1 not Found!");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "acc2":
			map.put("showEdit", "false");
			map.put("message2", "Account 2 not Found!");
			map.put("accountList", accountService.getAllAccounts());
			break;
		}
		Account accountResult = new Account();
		map.put("account", accountResult);
		return "account";
	}

	@RequestMapping(value = "account.withdraw", method = RequestMethod.GET, params = { "amount", "action" })
	public String withdraw(@RequestParam("amount") float amount, @RequestParam("action") String action,
			@ModelAttribute("account") Account account, BindingResult result, Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		String succ = accountService.withdrawMoney(account.getAccountNumber(), (float) amount);
		System.out.println(succ);
		switch (succ) {
		case "success":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity(
					"Withdrew from account: " + account.getAccountNumber());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			map.put("showEdit", "false");
			map.put("message3", "Successfully Withdrawn");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "fail":
			map.put("showEdit", "false");
			map.put("message4", "Insufficient Funds");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "acc1":
			map.put("showEdit", "false");
			map.put("message4", "Account not Found!");
			map.put("accountList", accountService.getAllAccounts());
			break;
		}
		Account accountResult = new Account();
		map.put("account", accountResult);
		return "account";
	}
	
	@RequestMapping(value = "account.deposit", method = RequestMethod.GET, params = { "amount", "action" })
	public String deposit(@RequestParam("amount") float amount, @RequestParam("action") String action,
			@ModelAttribute("account") Account account, BindingResult result, Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		String succ = accountService.depositMoney(account.getAccountNumber(), (float) amount);
		System.out.println(succ);
		switch (succ) {
		case "success":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity(
					"Deposited in account: " + account.getAccountNumber());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			map.put("showEdit", "false");
			map.put("message5", "Successfully Deposited");
			map.put("accountList", accountService.getAllAccounts());
			break;
		case "acc1":
			map.put("showEdit", "false");
			map.put("message6", "Account not Found!");
			map.put("accountList", accountService.getAllAccounts());
			break;
		}
		Account accountResult = new Account();
		map.put("account", accountResult);
		return "account";
	}
}
