package org.tourGo.controller.main.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.main.login.LoginService;

@Controller
@RequestMapping("/main")
public class SignController {
	
	@Autowired
	private LoginService loginService;
	
	private String fixed_url = "views/main/";
	
	@GetMapping("/login")
	public String login() {
		return fixed_url + "login";
	}
	
}
