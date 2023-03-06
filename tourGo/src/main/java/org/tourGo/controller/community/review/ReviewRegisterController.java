package org.tourGo.controller.community.review;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.service.community.review.ReviewSaveService;
import org.tourGo.services.file.FileUploadService;

@Controller
@RequestMapping("/community/review_register")
public class ReviewRegisterController {	
	
	@Autowired
	private ReviewSaveService reviewService;
	@Autowired
	private FileUploadService uploadService;
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo-여행후기";
	}


	private String baseUrl = "community/review/";
	
	private void addCommons(Model model) {
		//static & board명 추가
		model.addAttribute("board", "review");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"ckeditor/ckeditor",
				"community/review/form", "fileupload"});
		
		//지역, 기간 목록
		String[] regionLists = {"광주", "대구", "대전", "부산", "서울", "울산", "인천", 
				"강원도", "경기도", "경상도", "전라도", "제주도", "충청도"};
		String[] periodLists = {"당일치기", "1박2일", "2박3일", "3박4일", "4박5일", "5박6일 이상"};
		
		model.addAttribute("regionLists", regionLists);
		model.addAttribute("periodLists", periodLists);
	}

	
	//여행후기 작성페이지
	@GetMapping
	public String form(ReviewRequest reviewRequest, Model model){
		
		//공통데이터 model에 담기
		addCommons(model);

		//그룹아이디 설정
		String gid = ""+System.currentTimeMillis();
		model.addAttribute("gid", gid);

		reviewRequest = new ReviewRequest();
		model.addAttribute("reviewRequest", reviewRequest);

		return baseUrl + "review_register";
	}
	
	//후기 등록
	@PostMapping
	public String process(@Valid ReviewRequest reviewRequest, Errors errors, 
									@AuthenticationPrincipal PrincipalDetail principal,Model model) {		
		
		if (errors.hasErrors()) {
			//공통데이터 model에 담기
			addCommons(model);	
			
			return baseUrl + "review_register";
		}		
		
		String user = principal.getUsername();

		//내용 등록		
		try {						
			reviewRequest.setId(user);
			reviewRequest = reviewService.process(reviewRequest);
			
			// 파일 업로드 완료 처리 
			uploadService.updateSuccess(reviewRequest.getGid());
			
		} catch (Exception e) {
			errors.reject("review_write_error", e.getMessage());
			//공통데이터 model에 담기
			addCommons(model);			
			
			return baseUrl + "review_register";
		}
				
		return "redirect:/community/review_read/reviewNo_"+reviewRequest.getReviewNo();
	}
}
