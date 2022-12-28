package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.QReviewEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

import com.querydsl.core.BooleanBuilder;

@Service
public class ReviewService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;

	
	//(공통) 커맨드 -> entity
	private ReviewEntity requestToEntity(ReviewRequest request) {
		ReviewEntity entity = null;
		
		// 후기 수정일 때는 게시글 번호(reviewNo)로 기존 영속성 조회
		if (request.getReviewNo() != null) {
			entity = reviewRepository.findById(request.getReviewNo()).orElse(null);
		}
		
		if (entity == null) { // 후기 추가인 경우 새로운 entity 객체 생성 및 사용자 계정 생성, gid는 최초 추가시에만 생성 및 DB  처리
			entity = new ReviewEntity();
			User user = new User();
			user.setUserId(request.getId());
			entity.setUser(user);
			entity.setGid(request.getGid());
			entity.setReviewTitle(request.getReviewTitle());
			entity.setRegion(request.getRegion());
			entity.setPeriod(request.getPeriod());
			entity.setReviewContent(request.getReviewContent());			
		} 
		
		return entity;		
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
		
		if(keyword!=null) {//검색어 조회 조건
			QReviewEntity reviewEntity = QReviewEntity.reviewEntity;
			builder.or(reviewEntity.reviewTitle.contains(keyword))
					.or(reviewEntity.reviewContent.contains(keyword))
					.or(reviewEntity.region.contains(keyword));
		}
		String field = (order==null || !order.equals("read")) ? "regDt" : "reviewRead";
		Pageable pageable = PageRequest.of(page-1, limit, Sort.by(Direction.DESC, field));
		
		Page<ReviewEntity> lists = reviewRepository.findAll(builder, pageable);

		return lists;
	}
	
	//후기 등록하기
	@Transactional
	public ReviewRequest registerReview(ReviewRequest reviewRequest){

			User user = userRepository.findByUserId(reviewRequest.getId()).orElse(null);
			if(user==null) {
				throw new RuntimeException("아이디가 존재하지 않습니다.");
			}
			ReviewEntity entity = requestToEntity(reviewRequest);
			entity.setUser(user);
			
			entity = reviewRepository.save(entity);
			
			ReviewRequest request = new ReviewRequest(entity);
			
			return request;
	}

	//게시글 수정
	@Transactional
	public boolean updateReview(Long reviewNo, ReviewRequest reviewRequest){

		//기존 내용이라 DB조회 후 영속성 안으로 가져옴
		ReviewEntity reviewEntity = requestToEntity(reviewRequest);			

		try {
			reviewEntity.setReviewTitle(reviewRequest.getReviewTitle());
			reviewEntity.setReviewContent(reviewRequest.getReviewContent());
			reviewEntity.setRegion(reviewRequest.getRegion());
			reviewEntity.setPeriod(reviewRequest.getPeriod());
			reviewRepository.save(reviewEntity);
			
			return true;
		}catch(Exception e){
			
			return false;
			
		}
	}	
}
