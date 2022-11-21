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
		//전체목록
		List<ReviewRequest> lists = reviewService.getAllReviewList();	
		int totalCount = lists.size(); //총 게시물 수
		String[] addCss = {"community/community_common"}; //추가할 css 경로
		String[] addScript = {"community_common"}; //추가할 js 경로
		Map<String, Integer> pageMap = reviewService.paging();	
		
		model.addAttribute("lists", lists);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("startPage", pageMap.get("startPage"));
		model.addAttribute("endPage", pageMap.get("endPage"));
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림
		model.addAttribute("addCss", addCss); //css추가
		model.addAttribute("addScript", addScript); //js추가

		return "community/review/review_main";
	}
	
	//여행지 검색어로 조회하기
	@GetMapping("/review_search")
	public String searchRegion(@RequestParam String search, Model model) {
		model.addAttribute("search", search);
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] { "community_common"} );
		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read")
	public String readReview(@RequestParam int reviewNo, Model model) {
		ReviewRequest reviewRequest = reviewService.getOneReviewList(reviewNo);
		model.addAttribute("reviewRequest", reviewRequest);
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] { "community_common"} );
		return "community/review/review_read";
	}
}
