package org.tourGo.controller.community.review;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.service.community.reply.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글 등록
	@PostMapping("/reply/register")
	public String register(@RequestParam("no") Long reviewNo, @Valid ReplyRequest request, Errors errors,
							@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		
		String script = null;
		try {
			if (errors.hasErrors()) {
				
				for (ObjectError err : errors.getAllErrors()) {
					String message = err.getDefaultMessage();
					throw new RuntimeException(message);
				}				
			}		
			request.setReviewNo(reviewNo);
			
			//listOrder 설정
			String listOrder = request.getListOrder()==null? ""+System.currentTimeMillis() : request.getListOrder();
			request.setListOrder(listOrder);
			
			//depth, idParent 설정
			Long replyNo = request.getReplyNo();
			int depth = replyNo ==null ? 0 : 1;
			long idParent = replyNo == null ? 0 : replyNo;
			request.setDepth(depth);
			request.setIdParent(idParent);

			request.setId(principal.getUsername());
			request = replyService.register(request);
			
			script = "parent.location.replace('/community/review_read/reviewNo_" 
						+ reviewNo + "#comment_" + request.getReplyNo() + "');parent.location.reload();";
			
		} catch (RuntimeException e) {
			script = "alert('" + e.getMessage() + "');";
		}
		
		model.addAttribute("script", script);
		
		return "common/execution_script";		
	}
}
