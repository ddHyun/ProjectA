package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.community.review.LikedEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.LikedEntity;

@Service
public class LikedService {

	@Autowired
	private LikedEntityRepository likedRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	
	@Transactional
	public int process(String uid) {
		//기존 uid가 있다면 삭제 -> 좋아요 취소, 없다면 등록 -> 좋아요 클릭
		LikedEntity likedEntity = likedRepository.findById(uid).orElse(null);
		
		if(likedEntity==null) {//uid 조회결과가 없다면 추가(좋아요 클릭)
			likedEntity = LikedEntity.builder().uid(uid).build();
			likedRepository.save(likedEntity);
		}else {//조회결과가 있다면 삭제(좋아요 취소)
			likedRepository.delete(likedEntity);
		}
		
		//좋아요 총개수 
		long reviewNo = Long.parseLong(uid.split("_")[0]);
		int totalLikes = likedRepository.countByUid(reviewNo);
		//review 좋아요 총개수 업데이트
		reviewRepository.updateTotalLikes(totalLikes, reviewNo);
		return totalLikes;
	}
}
