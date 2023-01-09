package org.tourGo.controller.community.review;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tourGo.common.AlertException;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.community.review.LikedEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;

@Controller
public class ReviewViewController {

	@Autowired
	private ReviewEntityRepository repository;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private LikedEntityRepository likedRepository;


	private String baseUrl = "community/review/";
	
		
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/community/review_read/reviewNo_{reviewNo}")
	public String readReview(@PathVariable Long reviewNo, String keyword, String order, @AuthenticationPrincipal PrincipalDetail principal,
							@CookieValue(value="visitReview", required=false) Cookie cookie , Integer page, Model model){
		
		//css, js, board 추가
		model.addAttribute("board", "review");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"ckeditor/ckeditor",
				"community/review/read", "community/reply"});

		if(reviewNo==null) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		
		page = page==null? 1 : page;
		model.addAttribute("page", page);
		
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
		ReviewEntity entity = repository.findById(reviewNo).orElseThrow(()->
					new AlertException("게시글이 존재하지 않습니다", "/community/review_main"));
		ReviewRequest reviewRequest = new ReviewRequest(entity);		

		//댓글목록
		Comparator<ReplyEntity> compare = Comparator
																.comparing(ReplyEntity::getListOrder, Comparator.reverseOrder())
																.thenComparing(ReplyEntity::getReplyNo);
		List<ReplyRequest> replies = reviewRequest.getReplies().stream().sorted(compare).map(ReplyRequest::new).toList();
		model.addAttribute("replies", replies);

		model.addAttribute("reviewRequest", reviewRequest);	
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);		
		
		//댓글 커맨드 객체
		ReplyRequest replyRequest = new ReplyRequest();
		model.addAttribute("replyRequest", replyRequest);

		//댓글탬플릿에 보낼 정보
		model.addAttribute("no", reviewNo);

		Long userNo = null;
		
		if(principal != null) {
			model.addAttribute("user", principal.getUsername());
			userNo = principal.getUser().getUserNo();
		}	
			
		//좋아요 목록
		String uid = userNo != null ? ""+reviewNo+"_"+userNo : null;
		if(uid != null) {
			List<LikedRequest> likes = likedRepository.findById(uid).stream().map(LikedRequest::new).toList();
			model.addAttribute("likes", likes);
		}
			
		return baseUrl + "review_read";
	}
}
