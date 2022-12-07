package org.tourGo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.user.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@GetMapping("/user/mypage")
	public String mypage(Model model) {
		model.addAttribute("addScript", new String[] {"user/mypage"});
		return "user/mypage";
	}
	
}
