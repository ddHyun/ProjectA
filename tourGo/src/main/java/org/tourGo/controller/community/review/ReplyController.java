package org.tourGo.controller.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonResult;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.service.community.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ReplyEntityRepository replyRepository;
	
	//댓글 등록
	@PostMapping("/register")
	public JsonResult<Boolean> register(ReplyRequest request, Errors errors){

		request = replyService.register(request);
		JsonResult<Boolean> result = new JsonResult<>();
		result.setSuccess(true);
		
		return result;
	}
	
	//댓글 삭제
	@GetMapping("/remove")
	public JsonResult<Boolean> remove(Long replyNo){
//		replyService.remove(replyNo);
		replyRepository.deleteById(replyNo);
		JsonResult<Boolean> result = new JsonResult<>();
		result.setSuccess(true);
		
		return result;
	}
}
