package com.assig1.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assig1.business.PasswordEncryption;
import com.assig1.business.domainModel.Employee;
import com.assig1.business.domainModel.EmployeeActivities;
import com.assig1.business.services.EmployeeActionsService;
import com.assig1.business.services.EmployeeService;

@Controller("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeActionsService employeeActionsService;

	@RequestMapping(value = "/employee")
	public String ddoActions(@ModelAttribute Employee employee, BindingResult result,
			@ModelAttribute EmployeeActivities employeeActivities, BindingResult activitiesResult,
			Map<String, Object> map) {

		Employee employeeResult = new Employee();
		map.put("showEdit", "false");
		map.put("employee", employeeResult);
		map.put("employeeList", employeeService.getAllEmployees());
		return "employee";

	}

	@RequestMapping(value = "employee.do", method = RequestMethod.POST)
	public String doActions(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			@ModelAttribute EmployeeActivities employeeActivities, BindingResult activitiesResult,
			@RequestParam String action, Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername(); // get logged in username

		Employee employeeResult = new Employee();
		EmployeeActivities employeeActivity = new EmployeeActivities();

		long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
		} else {
			switch (action.toLowerCase()) {
			case "add":
				boolean ok = true;
				if (employeeService.getEmployeeByPNC(employee.getEmployeePersonalNumericalCode()) != null) {
					map.put("pncErrorMessage", "Pesronal numerical code already registred!");
					ok = false;
				}
				if (employeeService.getEmployeeByUsername(employee.getUsername()) != null) {
					map.put("usernameErrorMessage", "Username already taken!");
					ok = false;
				}

				if (ok) {
					employee.setPassword(PasswordEncryption.bcryptGen(employee.getPassword()));
					employeeService.add(employee);
					employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
					employeeActivity.setEmployeeActivity("Added employee employee with Personal Numerical Code: "
							+ employee.getEmployeePersonalNumericalCode());
					employeeActivity.setEmployeeActivityDate(new Date());
					employeeActionsService.add(employeeActivity);
					map.put("employee", employeeResult);
					map.put("showEdit", "true");
					map.put("employeel", employee);
				} else {
					map.put("showEdit", "false");
					map.put("employee", employee);
				}
				break;
			case "edit":
				ok = true;
				if (employeeService.getEmployeeByUsername(employee.getUsername()) != null
						&& (employeeService.getEmployeeByUsername(employee.getUsername())
								.getEmployeePersonalNumericalCode() != employee.getEmployeePersonalNumericalCode())) {
					map.put("usernameErrorMessage", "Username already taken!");
					ok = false;
					map.put("employee", new Employee());
				}
				if (ok) {
					if (!employee.getPassword().equals("")) {
						employee.setPassword(PasswordEncryption.bcryptGen(employee.getPassword()));
						employeeService.edit(employee);
						employeeResult = new Employee();
						map.put("employee", employeeResult);
					} else {
						employeeService.edit(employee);
						employeeResult = new Employee();
						map.put("employee", employeeResult);
					}
					System.out.println(currentUserPNC);
					employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
					employeeActivity.setEmployeeActivity("Edited employee with Personal Numerical Code: "
							+ employee.getEmployeePersonalNumericalCode());
					employeeActivity.setEmployeeActivityDate(new Date());
					employeeActionsService.add(employeeActivity);
					map.put("showEdit", "true");
					map.put("employeel", employee);
				}

				break;
			case "delete":
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Deleted employee with Personal Numerical Code: "
						+ employee.getEmployeePersonalNumericalCode());
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
				employeeService.delete(employee);
				employeeResult = employee;
				map.put("showEdit", "false");
				break;
			}
		}
		map.put("employeeList", employeeService.getAllEmployees());
		return "employee";
	}

	@RequestMapping(value = "employee.get", method = RequestMethod.GET, params = { "employeePersonalNumericalCode",
			"action" })
	public String search(@RequestParam("employeePersonalNumericalCode") long employeePersonalNumericalCode,
			@RequestParam("action") String action, @ModelAttribute("employee") Employee employee, BindingResult result,
			@ModelAttribute EmployeeActivities employeeActivities, BindingResult activitiesResult,
			Map<String, Object> map) {

		switch (action.toLowerCase()) {
		case "search":
			Employee searcherdEmployee = employeeService.getEmployeeByPNC(employeePersonalNumericalCode);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername(); // get logged in username
			EmployeeActivities employeeActivity = new EmployeeActivities();
			long currentUserPNC = employeeService.getEmployeeByUsername(username).getEmployeePersonalNumericalCode();

			if (searcherdEmployee != null) {
				List<Employee> list = new ArrayList<Employee>();
				list.add(searcherdEmployee);
				employeeActivity.setEmployeeActivityPersonalNumericalCode(currentUserPNC);
				employeeActivity.setEmployeeActivity("Searched for employee with Personal Numerical Code: "
						+ employee.getEmployeePersonalNumericalCode());
				employeeActivity.setEmployeeActivityDate(new Date());
				employeeActionsService.add(employeeActivity);
				map.put("employeel", searcherdEmployee);
				map.put("showEdit", "true");
				break;
			} else {
				map.put("showEdit", "false");
				map.put("message", "Employee not found");
				map.put("employeeList", employeeService.getAllEmployees());
				break;
			}
		case "show all":
			map.put("showEdit", "false");
			map.put("employeeList", employeeService.getAllEmployees());
			break;
		}
		Employee employeeResult = new Employee();
		map.put("employee", employeeResult);
		return "employee";
	}

	@RequestMapping(value = "employee.activity", method = RequestMethod.GET, params = {
			"employeeActivityPersonalNumericalCode", "employeeActivityDate", "employeeActivityDate2" })
	public String getActivities(@ModelAttribute("employeeActivities") EmployeeActivities employeeActivities,
			BindingResult resultActivities, @ModelAttribute("employee") Employee employee, BindingResult result,
			@RequestParam("employeeActivityPersonalNumericalCode") long employeeActivityPersonalNumericalCode,
			@RequestParam("employeeActivityDate") Date employeeActivityDate,
			@RequestParam("employeeActivityDate2") Date employeeActivityDate2, Map<String, Object> map) {
		Employee searcherdEmployee = employeeService.getEmployeeByPNC(employeeActivityPersonalNumericalCode);
		if (searcherdEmployee != null) {
			List<EmployeeActivities> list = employeeActionsService
					.get(searcherdEmployee.getEmployeePersonalNumericalCode());
			List<EmployeeActivities> checkList = new ArrayList<EmployeeActivities>();
			employeeActivityDate.setTime(employeeActivityDate.getTime() - 86400000);
			employeeActivityDate2.setTime(employeeActivityDate2.getTime() + 86400000);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmployeeActivityDate().after(employeeActivityDate)
						&& list.get(i).getEmployeeActivityDate().before(employeeActivityDate2)) {
					checkList.add(list.get(i));
				}
			}
			if (!checkList.isEmpty())
				map.put("employeeActivityList", checkList);
			else
				map.put("activityMessage", "No activities found in the given time period");

		} else {
			map.put("activityMessage", "Employee not found");

		}
		map.put("showEdit", "false");
		map.put("employeeActivities", new EmployeeActivities());
		map.put("employeeList", employeeService.getAllEmployees());
		return "employee";
	}
}
