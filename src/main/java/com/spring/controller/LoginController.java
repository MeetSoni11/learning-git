package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@GetMapping("/login")
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
		boolean isValidUser = service.validateUser(name,password);
		
		if(!isValidUser) {
			model.put("ErrorMessage", "Invalid Credentials");
			return "login";
		}
		
		model.put("name", name);
		model.put("password", password);
		
		return "welcome";
	}

}
