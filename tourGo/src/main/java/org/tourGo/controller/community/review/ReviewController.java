package org.tourGo.controller.community.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.commons.SearchRequest;
import org.tourGo.service.community.ReviewService;

@Controller
@RequestMapping("/community")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	HttpSession session;
	
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
	public String index(String search, Model model) {		
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("addCss", pathMap.get("addCss")); 
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		String s_search = (String)session.getAttribute("s_search");
		
		//목록 출력
		if(s_search==null) {
			//세션 없지만 검색어 있어 세션에 저장 후 검색리스트 가져옴
			if(search!=null) {
				session.setAttribute("s_search", search);
				List<ReviewRequest> searchLists = reviewService.searchList(search);
				model.addAttribute("lists", searchLists);
			}else {
				//전체목록(세션없고 검색어 없을때)
				List<ReviewRequest> lists = reviewService.getAllReviewList();	
				model.addAttribute("lists", lists);
			}
		}else {
			//세션 있지만 검색어 달라 세션 업데이트 후 검색리스트 가져옴
			if(!s_search.equals(search)&&search!=null) {
				session.setAttribute("s_search", search);
				List<ReviewRequest> searchLists = reviewService.searchList(search);
				model.addAttribute("lists", searchLists);
			}else {
				//세션 있고 검색어 같을때
				List<ReviewRequest> searchLists = reviewService.searchList(s_search);
				model.addAttribute("lists", searchLists);
			}
		}
		
		Map<String, Integer> pageMap = reviewService.paging();		
		model.addAttribute("pageMap", pageMap);
		
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림

		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read/{reviewNo}")
//	@GetMapping("/review_read")
	public String readReview(@PathVariable int reviewNo, Model model) {
		System.out.println("=============="+reviewNo);
		//글번호에 맞는 후기 가져오기
		ReviewRequest reviewRequest = reviewService.getOneReviewList(reviewNo);
		model.addAttribute("reviewRequest", reviewRequest);
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		return "community/review/review_read";
	}
}
