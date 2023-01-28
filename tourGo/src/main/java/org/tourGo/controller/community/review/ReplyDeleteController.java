package org.tourGo.controller.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.common.AlertException;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.service.community.reply.ReplyDeleteService;

@Controller
public class ReplyDeleteController {
	
	@Autowired
	private ReplyEntityRepository replyRepository;
	@Autowired
	private ReplyDeleteService replyDeleteService;
	
	//댓글 삭제
	@GetMapping("/reply/remove")
	public String process(Long replyNo, Model model){
		if(replyNo==null) {
			throw new AlertException("댓글 번호가 존재하지 않습니다", "back");
		}
		
		ReplyEntity replyEntity = replyRepository.findById(replyNo)
											.orElseThrow(() -> new AlertException("댓글이 존재하지 않습니다", "back"));
		long reviewNo = replyEntity.getReview().getReviewNo();
		
		replyDeleteService.process(replyEntity);
		
		String script = "parent.location.replace('/community/review_read/reviewNo_"
									+reviewNo+"');parent.location.reload();";
		model.addAttribute("script", script);
		return "common/execution_script";
	}
}
