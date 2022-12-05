package org.tourGo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class LoginController {
	
	private String fixed_url = "user/";
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
								@RequestParam(value="exception", required=false) String exception
								, Model model) {
		
		// 로그인 실패 시 error 표기 model로 보내줌
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return fixed_url + "login";
	}
	
	@PostMapping("/login")
	public String loginPs() {
		return "redirect:/";
	}
}
