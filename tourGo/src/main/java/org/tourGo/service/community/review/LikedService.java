package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.community.review.UidEntityRepository;
import org.tourGo.models.entity.community.review.UidEntity;

@Service
public class LikedService {

	@Autowired
	private UidEntityRepository uidRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	
	@Transactional
	public int process(String uid, String field) {
		//기존 uid가 있다면 삭제, 없다면 등록
		UidEntity uidEntity = uidRepository.findByFieldAndUid(field, uid).orElse(null);
		try {
			if(uidEntity==null) {//uid 조회결과가 없다면 추가
				uidEntity = UidEntity.builder().uid(uid).field(field).build();
				uidRepository.save(uidEntity);
			}else {//조회결과가 있다면 삭제
				uidRepository.delete(uidEntity);
			}
		}catch(RuntimeException e) {
			throw new AlertException("처리 도중 문제가 발생했습니다. 다시 시도해 주세요", "back");
		}
		
		//좋아요 총개수 
		long reviewNo = Long.parseLong(uid.split("_")[0]);
		int totalLikes = uidRepository.countByUid(field, reviewNo);
		//review 좋아요 총개수 업데이트
		reviewRepository.updateTotalLikes(totalLikes, reviewNo);
		return totalLikes;
	}
}
