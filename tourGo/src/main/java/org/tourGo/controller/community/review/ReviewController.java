package org.tourGo.controller.community.review;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.models.file.FileInfo;
import org.tourGo.service.community.ReviewService;
import org.tourGo.services.file.FileRUDService;
import org.tourGo.services.file.FileUploadService;

@Controller
@RequestMapping("/community")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileUploadService uploadService;
	@Autowired
	private FileRUDService fileService;
	@Autowired
	HttpSession session;


	String[] regionLists = {"광주", "대구", "대전", "부산", "서울", "울산", "인천", 
							"강원도", "경기도", "경상도", "전라도", "제주도", "충청도"};
	String[] periodLists = {"당일치기", "1박2일", "2박3일", "3박4일", "4박5일", "5박6일 이상"};
	
	private String baseUrl = "community/review/";
	
	//static 정보
	private void addCssJs(String boardName, String[] cssList, String[] jsList, Model model) {
		model.addAttribute("board", boardName);
		model.addAttribute("addCss", cssList);
		model.addAttribute("addScript", jsList);
	}
	
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(ReviewSearchRequest searchRequest, Model model) {		
		//css, js, board 추가
		addCssJs("review", new String[] {"community/community_common"}, 
				new String[] {"community/community_common"}, model);

		
		if(searchRequest.getKeyword() == null) {
			//전체목록 조회
			List<ReviewRequest> allLists = reviewService.getAllReviewList();	
			model.addAttribute("lists", allLists);
		}else {
			//검색어 조회			
			List<ReviewRequest> searchLists = reviewService.searchList(searchRequest);
			model.addAttribute("lists", searchLists);
		}
		
		model.addAttribute("searchRequest", searchRequest);

		return baseUrl+ "review_main";
	}
	
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read/reviewNo_{reviewNo}")
	public String readReview(@PathVariable int reviewNo, String keyword, Model model) {
		
		//css, js, board 추가
		addCssJs("review", new String[] {"community/community_common"}, 
				new String[] {"community/community_common", "ckeditor/ckeditor"}, model);

		//조회수 증가 글번호에 맞는 후기 가져오기
		reviewService.updateReviewRead(reviewNo);
		ReviewRequest reviewRequest = reviewService.getOneReviewList(reviewNo);
		List<FileInfo> fileLists = fileService.getFileLists(reviewRequest.getGid());
		System.out.println("===============fileLists : "+fileLists);
		model.addAttribute("reviewRequest", reviewRequest);		
		model.addAttribute("fileLists", fileLists);
		
		model.addAttribute("keyword", keyword);
		
		return baseUrl + "review_read";
	}
	
	//작성페이지
	@GetMapping("/review_form")
	public String moveToFillForm(ReviewRequest reviewRequest, String gid, Model model) {
		
//		미로그인 시 로그인 페이지로 이동
//		if(!session.getAttribute("sessionId")) {
//			return "redirect:/login";
//		}
		
		//css, js, board 추가
		addCssJs("review", new String[] {"community/community_common"}, 
				new String[] {"community/community_common", "ckeditor/ckeditor",
						"community/review/form", "fileupload"}, model);

		//지역, 기간 목록
		model.addAttribute("regionLists", regionLists);
		model.addAttribute("periodLists", periodLists);

		//그룹아이디 설정
		gid = gid==null? ""+System.currentTimeMillis() : gid;
		model.addAttribute("gid", gid);
		
		return baseUrl + "review_form";
	}
	
	//후기 등록하기
	@PostMapping("/review_register")
	public String registerReview(@Valid ReviewRequest reviewRequest, Errors errors, Model model) {		
		
		if (errors.hasErrors()) {
			//css, js, board 추가
			addCssJs("review", new String[] {"community/community_common"}, 
					new String[] {"community/community_common", "ckeditor/ckeditor",
							"community/review/form", "fileupload"}, model);
			return baseUrl + "review_form";
		}		
		
		String sessionId = "user02";//세션에 저장된 아이디로 바꾸기
		reviewRequest.setId(sessionId);
		
		//내용 등록		
		try {
			String period = periodLists[Integer.valueOf(reviewRequest.getPeriod())];
			String region = regionLists[Integer.valueOf(reviewRequest.getRegion())];
			reviewRequest.setPeriod(period);
			reviewRequest.setRegion(region);
			reviewService.registerReview(reviewRequest);
			
			// 파일 업로드 완료 처리 
			uploadService.updateSuccess(reviewRequest.getGid());
			
		} catch (Exception e) {
			errors.reject(e.getMessage(), "review_write_error");
			//css, js, board 추가
			addCssJs("review", new String[] {"community/community_common"}, 
					new String[] {"community/community_common", "ckeditor/ckeditor",
							"community/review/form", "fileupload"}, model);
			return baseUrl + "review_form";
		}
				
		return "redirect:/community/review_main";
	}
}
