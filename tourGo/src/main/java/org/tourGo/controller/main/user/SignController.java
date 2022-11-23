package org.tourGo.controller.main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String signUp(Model model) {
		
		SignRequest signRequest = new SignRequest();
		model.addAttribute("signRequest", signRequest);
		return fixed_url + "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUpPs(SignRequest request) {
		
		signService.process(request);
		
		return fixed_url + "login";
	}
	
}
