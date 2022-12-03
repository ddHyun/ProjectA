package org.tourGo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SignController {
	
	private String fixed_url = "user/";
	
	@GetMapping("/signCondition")
	public String signCondition(Model model) {
		model.addAttribute("addCss", new String[] {"user/sign"});
		
		// 세션 추가(동의)
		return fixed_url + "signCondition";
	}
	
	@GetMapping("/signUp")
	public String signUp(Model model) {
		SignRequest signRequest = new SignRequest();
		model.addAttribute("addCss", new String[] {"user/sign"});
		model.addAttribute("addScript", new String[] {"user/signUp"});
		model.addAttribute("signRequest", signRequest);
		return fixed_url + "signUp";
	}
}
