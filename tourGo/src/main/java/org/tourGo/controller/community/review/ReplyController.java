package org.tourGo.controller.community.review;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.service.community.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ReplyEntityRepository replyRepository;
	
	//댓글 등록
	@PostMapping("/register")
	public String register(@RequestParam("no") Long reviewNo, @Valid ReplyRequest request, Errors errors, Model model) {
		
		String script = null;
		try {
			if (errors.hasErrors()) {
				
				for (ObjectError err : errors.getAllErrors()) {
					String message = err.getDefaultMessage();
					throw new RuntimeException(message);
				}				
			}		
			request.setReviewNo(reviewNo);
			request = replyService.register(request);
			
			script = "parent.location.replace('/community/review_read/reviewNo_" 
						+ reviewNo + "#comment_" + request.getReplyNo() + "');parent.location.reload();";
			
		} catch (RuntimeException e) {
			script = "alert('" + e.getMessage() + "');";
		}
		
		model.addAttribute("script", script);
		
		return "common/execution_script";		
	}
	
	//댓글 삭제
	@GetMapping("/remove")
	@ResponseBody
	public JsonResult<Boolean> remove(Long replyNo){
		replyRepository.deleteById(replyNo);
		JsonResult<Boolean> result = new JsonResult<>();
		result.setSuccess(true);
		
		return result;
	}
}
