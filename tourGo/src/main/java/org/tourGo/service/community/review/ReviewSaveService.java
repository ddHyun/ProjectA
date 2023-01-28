package org.tourGo.service.community.review;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

/*여행후기 등록&수정*/

@Service
public class ReviewSaveService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	@Autowired
	private ReplyEntityRepository replyRepository;


	@Transactional
	public ReviewRequest process(ReviewRequest reviewRequest) {
		Long reviewNo = reviewRequest.getReviewNo();
		User user = userRepository.findByUserId(reviewRequest.getId())
				.orElseThrow(() -> new AlertException("아이디가 존재하지 않습니다", "back"));
		
		ReviewEntity reviewEntity = null;
		
		//reviewNo가 있다면 수정, 없다면 등록
		if(reviewNo != null) {
			reviewEntity = reviewRepository.findById(reviewNo).orElse(null);
			for(ReplyEntity reply : reviewRequest.getReplies()) {
				reply.setReview(reviewEntity);
				replyRepository.save(reply);
			}
		}
		
		reviewEntity = ReviewEntity.builder()
												.reviewNo(reviewNo)
												.user(user)
												.region(reviewRequest.getRegion())
												.period(reviewRequest.getPeriod())
												.reviewContent(reviewRequest.getReviewContent())
												.reviewTitle(reviewRequest.getReviewTitle())
												.reviewRead(reviewRequest.getReviewRead())
												.gid(reviewRequest.getGid())
												.totalLikes(reviewRequest.getTotalLikes())
												.deleteYn('N')
												.build();
		
		reviewRepository.save(reviewEntity);
		
		return new ModelMapper().map(reviewEntity, ReviewRequest.class);
	}
}
