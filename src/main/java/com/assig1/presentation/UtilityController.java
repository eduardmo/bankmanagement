package com.assig1.presentation;

import java.util.ArrayList;
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

@Controller("/utility")
public class UtilityController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeActionsService employeeActionsService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/utility")
	public String setupForm(Map<String, Object> map) {
		return "utility";
	}

	@RequestMapping(value = "utility.process", method = RequestMethod.GET, params = {"bill", "customerPersonalNumericalCode",
			"amount", "action" })
	public String process(@RequestParam("customerPersonalNumericalCode") long customerPersonalNumericalCode, @RequestParam("amount") float amount, @RequestParam("action") String action,
			@RequestParam("bill") String bill,
			Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		
		boolean ok = false;
		List<Account> list = new ArrayList<Account>();
		list = accountService.getAllAccountsByPNC(customerPersonalNumericalCode);
		
		if(list.size() <=0 ){
			map.put("message2", "Customer Has No Accounts!");
			return "utility";
		}
		for(int i =0; i< list.size(); i++){
			long accNo = list.get(i).getAccountNumber();
			String succ = accountService.withdrawMoney(accNo, (float) amount);
			switch (succ) {
			case "success":
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Payed "+bill +" from account: " + accNo);
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
				map.put("message1", "Successfully Payed, from account: " + accNo);
				ok = true;
				return "utility";				
			}
		}
		if(!ok)
			map.put("message2", "Insufficinet funds");
		return "utility";
	}
}
