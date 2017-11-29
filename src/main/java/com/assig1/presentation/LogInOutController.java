package com.assig1.presentation;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assig1.business.services.EmployeeService;

@Controller
public class LogInOutController implements AuthenticationSuccessHandler {

	@RequestMapping("/logout")
	public String setupForm(Map<String, Object> map, HttpServletRequest request) {
		request.getSession(true).invalidate();
		return "redirect:/login";
	}

	@Override
	@RequestMapping("/")
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {
		/* Redirect on the successful authentication of the user */
		// String redirectAddress = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		if (role.contains("ADMIN")) {
			response.sendRedirect(response.encodeURL("employee"));
		} else {
			if (role.contains("USER"))
				response.sendRedirect(response.encodeURL("user"));
			else
				response.sendRedirect(response.encodeURL("login?error"));
		}
	}
}