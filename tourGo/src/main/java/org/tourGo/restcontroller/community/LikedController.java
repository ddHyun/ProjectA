package org.tourGo.restcontroller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.controller.community.review.LikedRequest;
import org.tourGo.models.community.review.LikedEntityRepository;
import org.tourGo.service.community.review.LikedService;

/*좋아요 기능*/

@RestController
public class LikedController {
	
	@Autowired
	private LikedService likedService;

	
	@RequestMapping("/liked")
	public JsonResult<Object> process(Long reviewNo, boolean liked, Long likedNo, Model model, 
						@AuthenticationPrincipal PrincipalDetail principal) {
		
		if(reviewNo==null) {
			throw new JsonException("유효한 게시글번호가 아닙니다");
		}
		
		long totalLikes = 0;
		
		try {
			LikedRequest likedRequest = new LikedRequest();
			if(likedNo != null) {
				likedRequest.setLikedNo(likedNo);
			}
			likedRequest.setUserId(principal.getUsername());
			likedRequest.setReviewNo(reviewNo);		
			likedRequest.setLiked(liked);
			
			totalLikes = likedService.process(likedRequest);
		} catch (Exception e) {
			throw new JsonException(e.getMessage());
		}
		
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(totalLikes);
		return result;
	}
}
