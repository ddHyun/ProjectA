package org.tourGo.service.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.controller.community.review.ReviewSearchRequest;
import org.tourGo.models.community.review.QReviewEntity;
import org.tourGo.models.community.review.ReviewDto;
import org.tourGo.models.community.review.ReviewEntity;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.community.review.UserTestRepository;
import org.tourGo.models.community.review.User_test;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReviewService {

	@Autowired
	private ReviewEntityRepository rRepository;
	@Autowired
	private UserTestRepository uRepository;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//(공통) entity -> 커맨드(List)
	public List<ReviewRequest> entityToRequest(List<ReviewEntity> lists){
		
		//entity -> dto
		List<ReviewDto> dtoLists = new ArrayList<>();
		for(ReviewEntity entity : lists) {
			ReviewDto dto = ReviewDto.entityToDto(entity);
			dtoLists.add(dto);
		}
		
		//dto -> review커맨드
		List<ReviewRequest> requestLists = new ArrayList<>();
		for(ReviewDto dto : dtoLists) {
			ReviewRequest request = ReviewRequest.dtoToRequest(dto);
			requestLists.add(request);
		}
		
		return requestLists;
	}
	
	//(공통) entity -> 커맨드(단일)
	public ReviewRequest entityToRequest(ReviewEntity entity) {
		ReviewDto reviewDto = ReviewDto.entityToDto(entity);
		ReviewRequest reviewRequest = ReviewRequest.dtoToRequest(reviewDto);
		
		return reviewRequest;
	}

	// 여행후기 모든 목록 조회
	public List<ReviewRequest> getAllReviewList() {		
			
		User_test user1 = new User_test();
		user1.setId("user02");
		user1.setName("사용자02");
		uRepository.save(user1);
		
		List<ReviewEntity> lists = rRepository.findAllByOrderByRegDtDesc();
		
		if(lists.size() > 0) {	//entity -> dto -> request
			List<ReviewRequest> requestLists = entityToRequest(lists);
			return requestLists;
		}else {
			return null;
		}		
	}
	
	// 한 가지 목록 조회
	public ReviewRequest getOneReviewList(int reviewNo) {
		
		ReviewEntity entityList = rRepository.findByReviewNo(reviewNo);
		//entity -> dto -> request
		ReviewRequest requestList = entityToRequest(entityList); 	

		return requestList;
	}
	
	// 검색어로 조회
	public List<ReviewRequest> searchList(ReviewSearchRequest searchRequest){
		String keyword = searchRequest.getKeyword();
		/*Querydsl*/
		QReviewEntity qReview = QReviewEntity.reviewEntity; //Querydsl로 쿼리생성 위해 QReviewEntity객체 사용
		JPAQuery<ReviewEntity> query = jpaQueryFactory.selectFrom(qReview)
													.where(qReview.reviewTitle.contains(keyword)
													.or(qReview.region.like("%"+keyword+"%"))
													.or(qReview.reviewContent.contains(keyword)))
													.orderBy(qReview.regDt.desc());
		List<ReviewEntity> searchLists = query.fetch(); //조회결과 리스트 반환
		
		List<ReviewRequest> requestLists = new ArrayList<>();
		
		if(searchLists.size() > 0) {	//entity -> dto -> request
			requestLists = entityToRequest(searchLists);
		}else {
			throw new RuntimeException("조회결과가 없습니다");
		}
		return requestLists;
	}
	
	//후기 등록하기
	public ReviewRequest registerReview(ReviewRequest reviewRequest) {
		//request -> dto -> entity
		ReviewDto dto = ReviewRequest.requestToDto(reviewRequest);
		ReviewEntity entity = ReviewDto.dtoToEntity(dto);
	
		entity = rRepository.save(entity);
		
		//entity -> dto -> request
		ReviewRequest request = entityToRequest(entity); 
		
		return request;
	}
	
	//조회수 증가
	public boolean updateReviewRead(int reviewNo) {
		int affectedRow = rRepository.updateReviewRead(reviewNo);
		return affectedRow > 0;
	}	 

}
