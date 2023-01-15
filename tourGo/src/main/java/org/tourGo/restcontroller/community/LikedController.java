package org.tourGo.restcontroller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.controller.community.review.UidRequest;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.community.review.UidEntityRepository;
import org.tourGo.service.community.review.LikedService;

/*좋아요 기능*/

@RestController
public class LikedController {
	
	@Autowired
	private LikedService likedService;
	@Autowired
	private UidEntityRepository uidRepository;

	
	@RequestMapping("/liked")
	public JsonResult<Object> process(Long reviewNo, String uid, Model model, 
						@AuthenticationPrincipal PrincipalDetail principal) {
		if(reviewNo==null) {
			throw new JsonException("유효한 게시글번호가 아닙니다");
		}
		
		int totalLikes = 0;
		String field = "liked";

		try {
//			uid = uid==""? UidRequest.getUid(reviewNo, principal.getUser().getUserNo()) : uid;
			uid = uid == "" ? UidRequest.getUid(reviewNo, principal.getUser().getUserNo()) : uid;
			totalLikes = likedService.process(uid, field);
		}catch(Exception e) {
			throw new JsonException(e.getMessage());
		}		
		
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(totalLikes);
		
		return result;
	}
}
