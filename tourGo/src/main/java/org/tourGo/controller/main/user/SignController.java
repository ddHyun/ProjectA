package org.tourGo.controller.main.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
	
	private String fixed_url = "main/";
	
	@GetMapping("/signCondition")
	public String signCondition(Model model) {
		model.addAttribute("addCss", new String[] {"main/sign"});
		return fixed_url + "signCondition";
	}
	
	@GetMapping("/signUp")
	public String signUp(Model model) {
		SignRequest signRequest = new SignRequest();
		model.addAttribute("addCss", new String[] {"main/sign"});
		model.addAttribute("addScript", new String[] {"main/signUp"});
		model.addAttribute("signRequest", signRequest);
		return fixed_url + "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUpPs(Model model, @Valid SignRequest request, Errors errors) {
		
		// 비밀번호 확인 검증
		new SignValidator().validate(request, errors);
		
		if(errors.hasErrors()) {
			model.addAttribute("addCss", new String[] {"main/sign"});
			model.addAttribute("addScript", new String[] {"main/signUp"});
			return fixed_url +"signUp";
			// return "redirect:/"+ fixed_url +"signUp";
		}
		
		signService.process(request);
		
		return "redirect:/" + fixed_url + "login";
	}
	
}
