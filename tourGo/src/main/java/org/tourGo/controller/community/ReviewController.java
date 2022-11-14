package org.tourGo.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/review")
public class ReviewController {
	
	@GetMapping("/review_main")
	public String index(Model model) {
		model.addAttribute("welcome", "안녕하세요!");
		return "community/review/review_main";
	}
}
