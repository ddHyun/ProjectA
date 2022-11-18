package org.tourGo.controller.community.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.service.community.ReviewService;

@Controller
@RequestMapping("/community")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(Model model) {		
		
		List<ReviewRequest> lists = reviewService.getAllReviewList();	
		Map<String, Integer> pageMap = reviewService.paging();
		
		model.addAttribute("lists", lists);
		model.addAttribute("startPage", pageMap.get("startPage"));
		model.addAttribute("endPage", pageMap.get("endPage"));
		return "community/review/review_main";
	}
	
	//여행지 검색어로 조회하기
	@GetMapping("/review_search")
	public String searchRegion(@RequestParam String search, Model model) {
		model.addAttribute("search", search);
		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지 이동
	@GetMapping("/review_read")
	public String readReview(int reviewNo, Model model) {
		//세션에 저장된 아이디 가져와서 글번호랑 아이디 같이 넘기기
		ReviewRequest reviewRequest = reviewService.getOneReview(reviewNo);
		model.addAttribute("reviewRequest", reviewRequest);
		return "community/review/review_read";
	}
}
