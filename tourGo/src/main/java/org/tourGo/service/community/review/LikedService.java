package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.controller.community.review.LikedRequest;
import org.tourGo.models.community.review.LikedEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.LikedEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class LikedService {

	@Autowired
	private LikedEntityRepository likedRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;
	
	//좋아요 클릭 : insert / 좋아요 취소 : update
	@Transactional
	public int process(LikedRequest likedRequest) {
		
		Long likedNo = likedRequest.getLikedNo();
		LikedEntity likedEntity = null;
		
		ReviewEntity reviewEntity = reviewRepository.findById(likedRequest.getReviewNo()).
				orElseThrow(() -> new AlertException("게시물이 존재하지 않습니다", "back"));
		User user = userRepository.findByUserId(likedRequest.getUserId()).orElse(null);
		
		if(likedNo != null) {//좋아요 식별자 있을 경우
			likedEntity = likedRepository.findById(likedNo).orElseThrow(() -> new AlertException("처리 도중 오류가 발생했습니다", "back"));
			System.out.println(likedEntity);
			System.out.printf("entityId : %s, requestId : %s", likedEntity.getUser().getUserId(), likedRequest.getUserId());
			if(likedEntity.getUser().getUserId().equals(likedRequest.getUserId())) {//좋아요 누른 사람이 같은 경우 update
				likedEntity.update(likedRequest.isLiked());
			}else{
				likedEntity = LikedEntity.builder()
													.user(user)
													.review(reviewEntity)
													.liked(likedRequest.isLiked())
													.build();		
			}
		}else {
			likedEntity = LikedEntity.builder()
												.user(user)
												.review(reviewEntity)
												.liked(likedRequest.isLiked())
												.build();			
			
		}
				
		likedRepository.save(likedEntity);
		//좋아요 클릭 총 개수
		int totalLikes = likedRepository.countByReviewAndLiked(reviewEntity, true);
		//reviewEntity 좋아요 개수 업데이트
		reviewRepository.updateTotalLikes(totalLikes, reviewEntity.getReviewNo());
		return totalLikes;
	}	
}
