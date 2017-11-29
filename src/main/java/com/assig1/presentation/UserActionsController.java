package com.assig1.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/user")
public class UserActionsController {

@RequestMapping("/user")
	public String mainpage(){
	return "user";
}
	@RequestMapping(value = "useractions", method = RequestMethod.GET, params = {"action" })
public String search(@RequestParam("action") String action) {
		switch(action.toLowerCase()){
		case "customer management":
			return "redirect:/customer";
		case "account management":
			return "redirect:/account";
		case "pay utility bills":
			return "redirect:/utility";
		}
	return "redirect:/user";
	}
}
