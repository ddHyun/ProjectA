package org.tourGo.service.community.review;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.tourGo.common.AlertException;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.QReviewEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;

import com.querydsl.core.BooleanBuilder;

@Service
public class ReviewInfoService {

	@Autowired
	private ReviewEntityRepository reviewRepository;

	
	// 여행후기 하나만 확인
	public ReviewRequest getOneReviewList(Long reviewNo) {
		ReviewEntity reviewEntity = reviewRepository.findById(reviewNo)
												.orElseThrow(() -> new AlertException("게시글이 존재하지 않습니다", "back"));
		ModelMapper mapper = new ModelMapper();
		return mapper.map(reviewEntity, ReviewRequest.class);
	}
	
	// 여행후기 모든 목록 조회	
	public Page<ReviewEntity> getAllReviewList(){
		return getAllReviewList(1);
	}
	
	public Page<ReviewEntity> getAllReviewList(int page){
		return getAllReviewList(page, 20, "date", null);
	}
	
	public Page<ReviewEntity> getAllReviewList(int page, String order){
		return getAllReviewList(page, 20, order, null);
	}
	
	public Page<ReviewEntity> getAllReviewList(int page, int limit, String order, String keyword) {
		limit = limit <= 0 ? 20 : limit;
		BooleanBuilder builder = new BooleanBuilder();
		
		QReviewEntity reviewEntity = QReviewEntity.reviewEntity;
		keyword = keyword == null || keyword == "" ? null : keyword;
		if(keyword!=null) {//검색어 조회 조건
			builder.or(reviewEntity.reviewTitle.contains(keyword))
					.or(reviewEntity.reviewContent.contains(keyword))
					.or(reviewEntity.region.contains(keyword));
		}
		//게시글 삭제여부 확인(N값만 출력하기)
		builder.and(reviewEntity.deleteYn.eq('N'));
		
		String field = "";
		order = order == null || order== ""? "date" : order;
		
		switch(order) {
			case "date" : 
				field = "regDt";
				break;
			case "read" :
				field = "reviewRead";
				break;
			case "liked" :
				field = "totalLikes";
				break;
		}
		Pageable pageable = PageRequest.of(page-1, limit, Sort.by(Direction.DESC, field));
		
		Page<ReviewEntity> lists = reviewRepository.findAll(builder, pageable);

		return lists;
	}	
}
