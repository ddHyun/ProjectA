package org.tourGo.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.community.ReviewService;

@Controller
@RequestMapping("/community/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/review_main")
	public String index(Model model) {
		model.addAttribute("welcome", "안녕하세요!");
		return "views/community/review/review_main";
	}
}
