package org.tourGo.controller.community.review;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReviewEntity;

@Controller
@RequestMapping("/community")
public class ReviewReadController {

	@Autowired
	private ReviewEntityRepository repository;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletResponse response;


	private String baseUrl = "community/review/";
	
		
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/review_read/reviewNo_{reviewNo}")
	public String readReview(@PathVariable Long reviewNo, String keyword, String order, 
							@CookieValue(value="visitReview", required=false) Cookie cookie , Model model) throws Exception{
		
		//css, js, board 추가
		model.addAttribute("board", "review");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"community/community_common", "ckeditor/ckeditor",
				"community/review/read", "community/reply"});

		/** 쿠키 처리 S */
		if(cookie!=null) {
			if(!cookie.getValue().contains("["+reviewNo+"]")) {
				cookie.setValue(cookie.getValue()+"_["+reviewNo+"]");
				response.addCookie(cookie);
				repository.updateReviewRead(reviewNo);
			}
		}else {
			Cookie newCookie = new Cookie("visitReview", "["+reviewNo+"]");
			response.addCookie(newCookie);
			repository.updateReviewRead(reviewNo);
		}
		/** 쿠키 처리 E */
		
		//게시글 가져오기
		ReviewEntity entity = repository.findById(reviewNo).orElse(null);
		ReviewRequest reviewRequest = new ReviewRequest(entity);
		List<ReplyRequest> replies = reviewRequest.getReplies().stream().map(ReplyRequest::new).toList();
		if(replies.size() != 0) {
			model.addAttribute("replies", replies);
		}
		model.addAttribute("reviewRequest", reviewRequest);	
		
		
		//댓글
		ReplyRequest replyRequest = new ReplyRequest();
		model.addAttribute("replyRequest", replyRequest);

		//댓글탬플릿에 보낼 정보
		model.addAttribute("no", reviewNo);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		
		return baseUrl + "review_read";
	}		
}
