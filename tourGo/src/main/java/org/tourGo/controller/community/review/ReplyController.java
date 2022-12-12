package org.tourGo.controller.community.review;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.community.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/register")
	public String register(@Valid ReplyRequest request, Errors errors){
		if(errors.hasErrors()) {
			
		}
		replyService.register(request);
		
		return "redirect:/community/review_read/reviewNo_"+request.getReviewNo();
	}
}
