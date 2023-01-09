package org.tourGo.service.community.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.controller.community.review.ReplyRequest;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyEntityRepository replyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	
	//커맨드 -> 엔티티
	public ReplyEntity requestToEntity(ReplyRequest request) {
		
			ReplyEntity entity = new ReplyEntity();
			entity.setReplyContent(request.getReplyContent());
			ReviewEntity review = new ReviewEntity();
			review.setReviewNo(request.getReviewNo());
			entity.setReview(review);
			User user = new User();
			user.setUserId(request.getId());
			entity.setUser(user);
			entity.setDepth(request.getDepth());
			entity.setIdParent(request.getIdParent());
			entity.setListOrder(request.getListOrder());
		
		return entity;
	}
	
	
	//댓글 등록하기
	public ReplyRequest register(ReplyRequest request) {

		//영속성에 불러오기
		User user = userRepository.findByUserId(request.getId()).orElse(null);
		ReviewEntity review = reviewRepository.findById(request.getReviewNo()).orElse(null);
		
		ReplyEntity entity = requestToEntity(request);
		entity.setUser(user);
		entity.setReview(review);
		entity = replyRepository.save(entity);
		ReplyRequest replyRequest = new ReplyRequest(entity);
		
		return replyRequest;
	}

	// 댓글 삭제처리
	@Transactional
	public void deleteReply(Long replyNo){
		ReplyEntity reply = replyRepository.findById(replyNo).orElseThrow();
		
		// reply.setDeleteYn("Y");
		// replyRepository.save(reply);
	}
	
}
