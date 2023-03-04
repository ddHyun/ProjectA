package org.tourGo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignController {
	
	private String fixed_url = "user/";
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo-회원가입";
	}
	@GetMapping("/user/signUp")
	public String signUp(Model model) {
		SignRequest signRequest = new SignRequest();
		model.addAttribute("addCss", new String[] {"user/sign"});
		model.addAttribute("addScript", new String[] {"user/signUp"});
		model.addAttribute("signRequest", signRequest);
		return fixed_url + "signUp";
	}
}
