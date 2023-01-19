package org.tourGo.restcontroller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.service.community.review.ReviewDeleteService;

@RestController
@RequestMapping("/community")
public class ReviewDeleteController {

	@Autowired
	private ReviewDeleteService reviewDeleteService;
	

	//게시글 삭제
	@GetMapping("/review_delete")
	public JsonResult<Boolean> deleteReview(Long reviewNo){
		
		if(reviewNo==null) {
			throw new JsonException("게시글 번호가 존재하지 않습니다");
		}
		
		reviewDeleteService.process(reviewNo);
		
		JsonResult<Boolean> result = new JsonResult<>();
		result.setSuccess(true);
		
		return result;
	}		
}
