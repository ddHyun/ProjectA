package org.tourGo.controller.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.service.community.ReviewService;

@Controller
@RequestMapping("/community/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/review_main")
	public String index(Model model) {
		model.addAttribute("welcome", "안녕하세요!");
		return "community/review/review_main";
	}
	
	@GetMapping("/search")
	public String searchRegion(@RequestParam String search, Model model) {
		System.out.println("search: "+search);
		model.addAttribute("search", search);
		return "community/review/review_main";
	}
}
