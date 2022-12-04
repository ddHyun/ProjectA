package org.tourGo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.user.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {
	
	private String fixed_url = "user/";
	
	@GetMapping("/login")
	public String login(Model model) {
		
		return fixed_url + "login";
	}
	
	@PostMapping("/login")
	public String loginPs() {
		return "redirect:/";
	}
}
