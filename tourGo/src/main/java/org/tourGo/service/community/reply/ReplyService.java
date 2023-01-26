package org.tourGo.service.community.reply;

import org.modelmapper.ModelMapper;
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
	
	//댓글 등록하기
	public ReplyRequest register(ReplyRequest request) {

		//영속성에 불러오기
		User user = userRepository.findByUserId(request.getId()).orElse(null);
		ReviewEntity review = reviewRepository.findById(request.getReviewNo()).orElse(null);
		
		ReplyEntity entity = ReplyEntity.builder()
													.user(user)
													.replyContent(request.getReplyContent())
													.depth(request.getDepth())
													.idParent(request.getIdParent())
													.listOrder(request.getListOrder())
													.deleteYn("N")
													.review(review)
													.build();
//		entity.setReview(review);
		
		entity = replyRepository.save(entity);
		
		return new ModelMapper().map(entity, ReplyRequest.class);
	}	
}
