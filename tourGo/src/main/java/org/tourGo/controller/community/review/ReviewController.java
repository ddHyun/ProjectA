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
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(Model model) {		
		
		List<ReviewRequest> lists = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			ReviewRequest reviewRequest = new ReviewRequest();
			reviewRequest.setReviewNo(i-1);
			reviewRequest.setName("사용자"+i);
			reviewRequest.setReviewTitle("제목"+i);
			reviewRequest.setRead(i+1);
			lists.add(reviewRequest);
		}
		
		model.addAttribute("lists", lists);
		return "community/review/review_main";
	}
	
	//검색어 입력
	@GetMapping("/search")
	public String searchRegion(@RequestParam String search, Model model) {
		model.addAttribute("search", search);
		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지 이동
	@GetMapping("/review_read")
	public String reviewRead(int reviewNo, Model model) {
		model.addAttribute("reviewNo", reviewNo);
		return "community/review/review_read";
	}
}
