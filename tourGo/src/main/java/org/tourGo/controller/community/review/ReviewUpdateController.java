package org.tourGo.controller.community.review;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.service.community.review.ReviewInfoService;
import org.tourGo.service.community.review.ReviewSaveService;
import org.tourGo.services.file.FileUploadService;

@Controller
@RequestMapping("/community/review_update")
public class ReviewUpdateController {
	
	@Autowired
	private ReviewInfoService reviewInfoService;
	@Autowired
	private ReviewSaveService reviewSaveService;
	@Autowired
	private FileUploadService uploadService;
	
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

	
	//여행후기 수정페이지
	@GetMapping("/{reviewNo}")
	public String form(@PathVariable long reviewNo, Model model) {
		addCommons(model);
		ReviewRequest reviewRequest = reviewInfoService.getOneReviewList(reviewNo);
		model.addAttribute("reviewRequest", reviewRequest);
		
		return baseUrl + "review_update";
	}
	
	@PostMapping
	public String process(@Valid ReviewRequest reviewRequest, Errors errors, 
									@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		if(errors.hasErrors()) {
			addCommons(model);
			return baseUrl + "review_update";
		}
		
		reviewRequest.setId(principal.getUsername());
		reviewRequest = reviewSaveService.process(reviewRequest);
		model.addAttribute("reviewRequest", reviewRequest);
		// 파일 업로드 완료 처리 
		uploadService.updateSuccess(reviewRequest.getGid());
		return "redirect:/community/review_read/reviewNo_"+reviewRequest.getReviewNo();
	}
}
