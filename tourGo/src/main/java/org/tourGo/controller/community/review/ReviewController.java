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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tourGo.models.file.FileInfo;
import org.tourGo.service.community.ReviewService;
import org.tourGo.services.file.FileUploadService;

@Controller
@RequestMapping("/community")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileUploadService uploadService;
	@Autowired
	HttpSession session;
	
	//application.properties에 사용할 css, js경로 추가
	@Value("${community.addCss}")
	private String[] addCss;
	@Value("${community.addScript}")
	private String[] addScript;
	
	
	String[] regionLists = {"광주", "대구", "대전", "부산", "서울", "울산", "인천", 
							"강원도", "경기도", "경상도", "전라도", "제주도", "충청도"};
	String[] periodLists = {"당일치기", "1박2일", "2박3일", "3박4일", "4박5일", "5박6일 이상"};
	
	//css, js 추가파일 경로
	private Map<String, String[]> getFileLists() {
		Map<String,	String[]> pathMap = new HashMap<>();
		pathMap.put("addCss", addCss);
		pathMap.put("addScript", addScript);
		
		return pathMap;
	}	
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(ReviewSearchRequest searchRequest, Model model) {		
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("addCss", pathMap.get("addCss")); 
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		if(searchRequest.getKeyword() == null) {
			//전체목록 조회
			List<ReviewRequest> allLists = reviewService.getAllReviewList();	
			System.out.println("=====================");
			System.out.println(allLists);
			model.addAttribute("lists", allLists);
		}else {
			//검색어 조회
			List<ReviewRequest> searchLists = reviewService.searchList(searchRequest);
			model.addAttribute("lists", searchLists);
		}
		
		model.addAttribute("searchRequest", searchRequest);
		
//		Map<String, Integer> pageMap = reviewService.paging();		
//		model.addAttribute("pageMap", pageMap);
		
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림

		return "community/review/review_main";
	}
	
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read/reviewNo_{reviewNo}")
	public String readReview(@PathVariable int reviewNo, String keyword, Model model) {
		
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림

		//글번호에 맞는 후기 가져오기
		ReviewRequest reviewRequest = reviewService.getOneReviewList(reviewNo);
		model.addAttribute("reviewRequest", reviewRequest);
		
		model.addAttribute("keyword", keyword);
		
		return "community/review/review_read";
	}
	
	//작성페이지
	@GetMapping("/review_form")
	public String moveToFillForm(ReviewRequest reviewRequest, String gid, Model model) {
		
//		미로그인 시 로그인 페이지로 이동
//		if(!session.getAttribute("sessionId")) {
//			return "redirect:/login";
//		}
		
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		//지역, 기간 목록
		model.addAttribute("regionLists", regionLists);
		model.addAttribute("periodLists", periodLists);
		model.addAttribute("board", "review"); //여행후기 게시판임을 알림

		//그룹아이디 설정
		gid = gid==null? ""+System.currentTimeMillis() : gid;
		model.addAttribute("gid", gid);
		
		return "community/review/review_form";
	}
	
	//후기 등록하기
	@PostMapping("/review_register")
	@ResponseBody
	public Map<String, Object> registerReview(@RequestParam(required=false) MultipartFile[] files, MultipartHttpServletRequest mr) {		

		String gid = mr.getParameter("gid");
		
		//양식데이터는 MultipartHttpServletRequest.getParameter로 가져오기
		String sessionId = "user02";//세션에 저장된 아이디로 바꾸기
		ReviewRequest reviewRequest = new ReviewRequest();
		reviewRequest.setId(sessionId);
		reviewRequest.setReviewTitle(mr.getParameter("reviewTitle"));
		String period = periodLists[Integer.valueOf(mr.getParameter("period"))];
		reviewRequest.setPeriod(period);
		String region = regionLists[Integer.valueOf(mr.getParameter("region"))];
		reviewRequest.setRegion(region);
		reviewRequest.setReviewContent(mr.getParameter("reviewContent"));
		reviewRequest.setGid(gid);
		
		//내용 등록
		List<FileInfo> fileLists = uploadService.process(files, gid);	
		ReviewRequest request = reviewService.registerReview(reviewRequest);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("fileLists", fileLists);
		resultMap.put("reviewRequest", request);
		return resultMap;
	}
}
