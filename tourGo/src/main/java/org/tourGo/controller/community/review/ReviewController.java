package org.tourGo.controller.community.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.models.entity.community.review.ReviewEntity;
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
	private HttpSession session;

	private String baseUrl = "community/review/";
	
	//static & board명 추가
	private void addCssJs(String boardName, String[] cssList, String[] jsList, Model model) {
		model.addAttribute("board", boardName);
		model.addAttribute("addCss", cssList);
		model.addAttribute("addScript", jsList);
	}
	
		
	//여행후기 메인페이지
	@GetMapping("/review_main")
	public String index(@RequestParam(name="page", required=false) Integer page, String keyword, String order, Model model){		
		//css, js, board 추가
		addCssJs("review", new String[] {"community/community_common", "community/pagination"}, 
				new String[] {"community/community_common", "community/review/index"}, model);	
		
		page = page == null? 1 : page;
		
		Page<ReviewEntity> results = reviewService.getAllReviewList(page, 10, order, keyword);
		//Page<엔티티>->List<엔티티>->Stream<엔티티>->Stream<커맨드>->List<커맨드>
		List<ReviewRequest> lists = results.getContent().stream().map(reviewService::entityToRequest).toList();
		
		String url = "/community/review_main";
		Pagination<ReviewEntity> pagination = new Pagination<>(results, url);
		
		model.addAttribute("lists", lists);
		model.addAttribute("pagination", pagination);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		
		//나중에 지우기
		session.setAttribute("user", "user01");
		
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
		String[] regionLists = {"광주", "대구", "대전", "부산", "서울", "울산", "인천", 
				"강원도", "경기도", "경상도", "전라도", "제주도", "충청도"};
		String[] periodLists = {"당일치기", "1박2일", "2박3일", "3박4일", "4박5일", "5박6일 이상"};
		
		model.addAttribute("regionLists", regionLists);
		model.addAttribute("periodLists", periodLists);

		//그룹아이디 설정
		gid = gid==null? ""+System.currentTimeMillis() : gid;
		model.addAttribute("gid", gid);
		
		//수정시 내용 가져오기
		if(reviewNo!=null) {
			reviewRequest = reviewService.getOneReviewList(reviewNo);			
			model.addAttribute("reviewRequest", reviewRequest);
		}
		
		return baseUrl + "review_form";
	}
	
	//후기 등록
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
		
		String user = (String)session.getAttribute("user");

		//내용 등록		
		try {						
			reviewRequest.setId(user);
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
