package org.tourGo.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.user.LoginService;

@Controller
@RequestMapping("/main")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	private String fixed_url = "main/";
	
	@GetMapping("/login")
	public String login(Model model) {
		
		LoginRequest loginRequest = new LoginRequest();
		model.addAttribute("loginRequest", loginRequest);
		
		return fixed_url + "login";
	}
	
	@PostMapping("/login")
	public String loginPs(@Valid LoginRequest loginRequest, Errors errors) {
		try {
			loginService.process(loginRequest, errors);
		} catch (RuntimeException e){
			errors.reject("loginError", e.getMessage());
		}
		
		if(errors.hasErrors()) {
			return fixed_url + "login";
		}
		return "redirect:/";
	}
}
