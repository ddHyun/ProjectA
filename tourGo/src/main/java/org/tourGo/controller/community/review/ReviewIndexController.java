package org.tourGo.controller.community.review;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.service.community.ReviewService;

@Controller
public class ReviewIndexController{
	
	@Autowired
	private ReviewService reviewService;
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
	@GetMapping("/community/review_main")
	public String index(@RequestParam(name="page", required=false) Integer page, String keyword, String order, Model model){		
		//css, js, board 추가
		addCssJs("review", new String[] {"community/community_common", "community/pagination"}, 
				new String[] {"community/community_common", "community/review/index"}, model);	
		
		page = page == null? 1 : page;
		
		Page<ReviewEntity> results = reviewService.getAllReviewList(page, 10, order, keyword);
		//Page<엔티티>->List<엔티티>->Stream<엔티티>->Stream<커맨드>->List<커맨드>
		List<ReviewRequest> lists = results.getContent().stream().map(ReviewRequest::new).toList();
		
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
}
