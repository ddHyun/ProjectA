package org.tourGo.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		
		ReplyEntity entity = null;
		Long replyNo = request.getReplyNo();
		
		if(replyNo != null) {//기존목록 있으면 DB에서 가져오기
			entity = replyRepository.findById(replyNo).orElse(null);
		}else {
			entity = new ReplyEntity();
			entity.setReplyContent(request.getReplyContent());
			ReviewEntity review = new ReviewEntity();
			review.setReviewNo(request.getReviewNo());
			entity.setReview(review);
			User user = new User();
			user.setUserId(request.getId());
			entity.setUser(user);
		}
		
		return entity;
	}
	
	//엔티티 -> 커맨드
	public ReplyRequest entityToRequest(ReplyEntity entity) {
		
		ReplyRequest request = new ReplyRequest();
		
		request.setId(entity.getUser().getUserId());
		request.setName(entity.getUser().getUserNm());
		request.setReplyNo(entity.getReplyNo());
		request.setReviewNo(entity.getReview().getReviewNo());
		request.setReplyContent(entity.getReplyContent());
		request.setRegDt(entity.getRegDt());
		request.setModDt(entity.getModDt());
		
		return request;
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
		ReplyRequest replyRequest = entityToRequest(entity);
		
		return replyRequest;
	}
}
