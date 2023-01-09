package org.tourGo.restcontroller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.service.community.review.LikedService;

/*좋아요 기능*/

@RestController
public class LikedController {
	
	@Autowired
	private LikedService likedService;
	
	@RequestMapping("/liked")
	public JsonResult<Object> process(Long reviewNo, String uid, Model model, 
						@AuthenticationPrincipal PrincipalDetail principal) {
		
		if(reviewNo==null) {
			throw new JsonException("유효한 게시글번호가 아닙니다");
		}
		int totalLikes = 0;

		try {
			uid = uid==null? ""+reviewNo+"_"+principal.getUser().getUserNo() : uid;
			System.out.println("=============");
			System.out.println("uid : "+uid);
			totalLikes = likedService.process(uid);
		}catch(Exception e) {
			throw new JsonException(e.getMessage());
		}		
		
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(totalLikes);
		
		return result;
	}
}
