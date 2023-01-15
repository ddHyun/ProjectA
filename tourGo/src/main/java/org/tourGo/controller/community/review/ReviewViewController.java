package org.tourGo.controller.community.review;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tourGo.common.AlertException;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.community.review.UidEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.community.review.UidEntity;
import org.tourGo.service.community.review.ReadHitService;

@Controller
public class ReviewViewController {

	@Autowired
	private ReviewEntityRepository reviewRepository;
	@Autowired
	private UidEntityRepository uidRepository;
	@Autowired
	private ReadHitService readService;

	private String baseUrl = "community/review/";
	
		
	//제목 클릭시 후기읽기 페이지
	@GetMapping("/community/review_read/reviewNo_{reviewNo}")
	public String readReview(@PathVariable Long reviewNo, String keyword, String order, 
										@AuthenticationPrincipal PrincipalDetail principal,
										Integer page, Model model){
		
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
		
		Long userNo = null;
		
		if(principal != null) {
			model.addAttribute("user", principal.getUsername());
			userNo = principal.getUser().getUserNo();
			//로그인 유저의 '좋아요' 클릭 목록
			UidEntity uidEntity = uidRepository.findByNo("liked", reviewNo, userNo).orElse(null);
			if(uidEntity != null) {
				UidRequest like = new UidRequest(uidEntity);
				model.addAttribute("like", like);
			}
		}
		
		//조회수
		String readUid = UidRequest.getUid(reviewNo, userNo);
		readService.process(readUid, "readHit", "review");	

		//게시글 가져오기
		ReviewEntity entity = reviewRepository.findById(reviewNo).orElseThrow(()->
					new AlertException("게시글이 존재하지 않습니다", "/community/review_main"));
		ReviewRequest reviewRequest = new ReviewRequest(entity);		

		//댓글목록
		Comparator<ReplyEntity> compare = Comparator
																.comparing(ReplyEntity::getListOrder, Comparator.reverseOrder())
																.thenComparing(ReplyEntity::getReplyNo);
		List<ReplyRequest> replies = reviewRequest.getReplies().stream().sorted(compare).map(ReplyRequest::new).toList();
		model.addAttribute("replies", replies);

		model.addAttribute("reviewRequest", reviewRequest);	
		
		//목록으로 돌아가기 url
		String url = "/community/review_main?keyword="+keyword+"&order="+order+"&page="+page;
		model.addAttribute("url", url);
		
		//댓글 커맨드 객체
		ReplyRequest replyRequest = new ReplyRequest();
		model.addAttribute("replyRequest", replyRequest);

		//댓글탬플릿에 보낼 정보
		model.addAttribute("no", reviewNo);
					
		return baseUrl + "review_read";
	}
}
