package org.tourGo.restcontroller.community;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonResult;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.service.community.ReviewService;

@RestController
@RequestMapping("/community")
public class ReviewRestController {
	
	@Autowired
	private ReviewEntityRepository repository;
	@Autowired
	private ReviewService reviewService;
	


	//게시글 삭제
	@GetMapping("/review_delete")
	public JsonResult<Boolean> deleteReview(Long reviewNo){
		if(reviewNo==null) {
			//return 게시글이 존재하지 않습니다 오류
		}
		repository.deleteById(reviewNo);
		
		JsonResult<Boolean> result = new JsonResult<>();
		result.setSuccess(true);
		
		return result;
	}	
	
	//게시글 수정 
	@PostMapping("/review_update")
	private JsonResult<Boolean> modifyPost(@Valid ReviewRequest reviewRequest, Errors errors){
	
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.joining(","));
			throw new RuntimeException(msg);
		}
		
		Long reviewNo = reviewRequest.getReviewNo();
		JsonResult<Boolean> result = new JsonResult<>();		
		boolean isSuccess = reviewService.updateReview(reviewNo, reviewRequest);
		result.setSuccess(isSuccess);
		
		return result;			
	}	
	
	//조회 정렬 선택
	@GetMapping("/by_order")
	public JsonResult<List<ReviewRequest>> orderBy(String order, String keyword){
		
		if(order==null) {//기본정렬 : 최신순
			order="date";
		}
		
		List<ReviewRequest> lists = new ArrayList<>();
		
		if(keyword==null) {
			lists = reviewService.getAllReviewList(order);
		}else {
			lists = reviewService.searchList(keyword, order);
		}
		
		JsonResult<List<ReviewRequest>> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(lists);
		
		return result;
	}
}
