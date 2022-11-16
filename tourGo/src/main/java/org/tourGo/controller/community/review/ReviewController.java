package org.tourGo.controller.community.review;

import java.util.ArrayList;
import java.util.List;

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
		
		List<ReviewRequest> lists = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			ReviewRequest reviewRequest = new ReviewRequest();
			reviewRequest.setName("사용자"+i);
			reviewRequest.setReviewTitle("제목"+i);
			reviewRequest.setRead(i+1);
			lists.add(reviewRequest);
		}
		
		model.addAttribute("lists", lists);
		return "community/review/review_main";
	}
	
	@GetMapping("/search")
	public String searchRegion(@RequestParam String search, Model model) {
		System.out.println("search: "+search);
		model.addAttribute("search", search);
		return "community/review/review_main";
	}
}
