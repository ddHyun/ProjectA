package org.tourGo.controller.main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.main.user.SignService;

@Controller
@RequestMapping("/main")
public class SignController {
	
	@Autowired
	private SignService signService;
	
	private String fixed_url = "views/main/";
	
	@GetMapping("/signCondition")
	public String signCondition() {
		return fixed_url + "signCondition";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return fixed_url + "signUp";
	}
	
}
