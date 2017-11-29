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

import com.assig1.business.domainModel.Customer;
import com.assig1.business.domainModel.EmployeeActivities;
import com.assig1.business.services.CustomerService;
import com.assig1.business.services.EmployeeActionsService;
import com.assig1.business.services.EmployeeService;

@Controller("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeActionsService employeeActionsService;
	
	
	@RequestMapping("/customer")
	public String setupForm(Map<String, Object> map) {
		Customer customer = new Customer();
		map.put("showEdit", "false");
		map.put("customer", customer);
		map.put("customerList", customerService.getAllCustomers());
		return "customer";
	}

	@RequestMapping(value = "customer.do", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Customer customer, BindingResult result, @RequestParam String action,
			Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username
		EmployeeActivities employeeActivity = new EmployeeActivities();
		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		Customer customerResult = new Customer();
		switch (action.toLowerCase()) {
		case "add":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity("Added customer with PNC: " +customer.getCustomerPersonalNumericalCode());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			customerService.add(customer);
			map.put("customerl", customer);
			break;
		case "edit":
			employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
			employeeActivity.setEmployeeActivity("Edited customer with PNC: " +customer.getCustomerPersonalNumericalCode());
			employeeActivity.setEmployeeActivityDate(new Date());
			employeeActionsService.add(employeeActivity);
			customerService.edit(customer);
			map.put("customerl", customer);
			break;
		}
		map.put("showEdit", "true");
		map.put("customer", customerResult);
		return "customer";
	}

	@RequestMapping(value = "customer.get", method = RequestMethod.GET, params = { "customerPersonalNumericalCode",
			"action" })
	public String search(@RequestParam("customerPersonalNumericalCode") long customerPersonalNumericalCode,
			@RequestParam("action") String action, @ModelAttribute("customer") Customer customer, BindingResult result,
			Map<String, Object> map) {
		
		switch (action.toLowerCase()) {
		case "search":
			Customer searcherdCustomer = customerService.get(customerPersonalNumericalCode);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername(); // get logged in username
			EmployeeActivities employeeActivity = new EmployeeActivities();
			long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();

			if (searcherdCustomer != null) {
				List<Customer> list = new ArrayList<Customer>();
				list.add(searcherdCustomer);
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Searched for customer with Personal Numerical Code: "
						+ customer.getCustomerPersonalNumericalCode());
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
				map.put("customerl", searcherdCustomer);
				map.put("showEdit", "true");
				break;
			} else {
				map.put("showEdit", "false");
				map.put("message", "Customer not found");
				map.put("customerList", customerService.getAllCustomers());
				break;
			}
		case "show all":
			map.put("showEdit", "false");
			map.put("customerList", customerService.getAllCustomers());
			break;
		}
		Customer customerResult = new Customer();
		map.put("customer", customerResult);
		return "customer";
	}

}
