package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReviewEntity;

/*여행후기 삭제*/

@Service
public class ReviewDeleteService {

	@Autowired
	private ReviewEntityRepository reviewRepository;
	
	@Transactional
	public void process(Long reviewNo){
		ReviewEntity review = reviewRepository.findById(reviewNo)
										.orElseThrow(() -> new AlertException("게시글이 존재하지 않습니다", "back"));
		
		review.setDeleteYn('Y');
		
		reviewRepository.save(review);
	}
}
