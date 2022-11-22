package org.tourGo.controller.community.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.service.community.ReviewService;

@Controller
@RequestMapping("/community")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	//application.properties에 사용할 css, js경로 추가
	@Value("${community.addCss}")
	private String[] addCss;
	@Value("${community.addScript}")
	private String[] addScript;
	
	
	//css, js 추가파일 경로
	private Map<String, String[]> getFileLists() {
		Map<String,	String[]> pathMap = new HashMap<>();
		pathMap.put("addCss", addCss);
		pathMap.put("addScript", addScript);
		
		return pathMap;
	}
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(Model model) {		
		//전체목록
		List<ReviewRequest> lists = reviewService.getAllReviewList();	
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		Map<String, Integer> pageMap = reviewService.paging();
		
		model.addAttribute("lists", lists);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림
		model.addAttribute("addCss", pathMap.get("addCss")); //css추가
		model.addAttribute("addScript", pathMap.get("addScript")); //js추가

		return "community/review/review_main";
	}
	
	//여행지 검색어로 조회하기
	@GetMapping("/review_search")
	public String searchRegion(@RequestParam String search, Model model) {
		model.addAttribute("search", search);
		
		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read/{reviewNo}")
	public String readReview(@PathVariable int reviewNo, Model model) {
		//글번호에 맞는 후기 가져오기
		ReviewRequest reviewRequest = reviewService.getOneReviewList(reviewNo);
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		
		model.addAttribute("reviewRequest", reviewRequest);
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		return "community/review/review_read";
	}
}
