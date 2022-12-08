package org.tourGo.controller.community.review;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.service.community.ReviewService;
import org.tourGo.services.file.FileUploadService;

@Controller
@RequestMapping("/community")
public class ReviewController{
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileUploadService uploadService;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;


	String[] regionLists = {"광주", "대구", "대전", "부산", "서울", "울산", "인천", 
							"강원도", "경기도", "경상도", "전라도", "제주도", "충청도"};
	String[] periodLists = {"당일치기", "1박2일", "2박3일", "3박4일", "4박5일", "5박6일 이상"};
	
	private String baseUrl = "community/review/";
	
	//static & board명 추가
	private void addCssJs(String boardName, String[] cssList, String[] jsList, Model model) {
		model.addAttribute("board", boardName);
		model.addAttribute("addCss", cssList);
		model.addAttribute("addScript", jsList);
	}
	
	//ajax 
	@PostMapping("/review_update")
	@ResponseBody
	private JsonResult<Object> sendSuccessResult(@Valid ReviewRequest reviewRequest, Errors errors){
		JsonResult<Object> result = new JsonResult<>();
		

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.joining(","));
			throw new RuntimeException(msg);
		}
		
		Long reviewNo = reviewRequest.getReviewNo();
		boolean isSuccess = reviewService.updateReview(reviewNo, reviewRequest);
		result.setSuccess(isSuccess);
		
		
		System.out.println("====================");
		System.out.printf("json.success : %s, json.data : %s", result.isSuccess(), result.getData());
		return result;
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	private JsonResult<Object> sendFailResult(String message){
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(message);
		
		return result;
	}
	
	
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(ReviewSearchRequest searchRequest, Model model) throws Exception{		
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
	
	
	//작성&수정페이지
	@GetMapping({"/review_form", "/review_modify"})
	public String moveToFillForm(ReviewRequest reviewRequest, String gid, 
									Long reviewNo, Model model) throws Exception{
		
//		미로그인 시 로그인 페이지로 이동
//		if(!session.getAttribute("user")) {
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
		
		//수정시 내용 가져오기
		if(reviewNo!=null) {
			reviewRequest = reviewService.getOneReviewList(reviewNo);
			
			int period = 0; 
			int region = 0;
			
			for(int i=0; i<periodLists.length; i++) {
				if(reviewRequest.getPeriod().equals(periodLists[i])) {
					period = i;
				}
				if(reviewRequest.getRegion().equals(regionLists[i])) {
					region = i;
				}
			}
			model.addAttribute("selectedPeriod", String.valueOf(period));
			model.addAttribute("selectedRegion", String.valueOf(region));
			model.addAttribute("reviewRequest", reviewRequest);
		}
		
		return baseUrl + "review_form";
	}
	
	//후기 등록&수정
	@PostMapping("/review_register")
	public String registerReview(@Valid ReviewRequest reviewRequest, Errors errors,
											Long reviewNo, Model model) {		
		
		if (errors.hasErrors()) {
			//css, js, board 추가
			addCssJs("review", new String[] {"community/community_common"}, 
					new String[] {"community/community_common", "ckeditor/ckeditor",
							"community/review/form", "fileupload"}, model);
			return baseUrl + "review_form";
		}		
		
		String sessionUser = "user02";//세션에 저장된 아이디로 바꾸기
		//내용 등록		
		try {			
			String period = periodLists[Integer.valueOf(reviewRequest.getPeriod())];
			String region = regionLists[Integer.valueOf(reviewRequest.getRegion())];
			reviewRequest.setPeriod(period);
			reviewRequest.setRegion(region);
			reviewRequest.setId(sessionUser);
			
			//if(reviewNo != null) {//글 수정시
				
			//}else {//글 등록시
				reviewService.registerReview(reviewRequest);
			//}
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
