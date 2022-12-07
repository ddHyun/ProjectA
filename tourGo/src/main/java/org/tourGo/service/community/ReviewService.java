package org.tourGo.service.community;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.controller.community.review.ReviewSearchRequest;
import org.tourGo.models.entity.community.review.QReviewEntity;
//import org.tourGo.models.community.review.ReviewDto;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReviewService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//(공통) 커맨드 -> entity
	private ReviewEntity requestToEntity(ReviewRequest request) {
		ReviewEntity entity = new ReviewEntity();
		entity.setReviewNo(request.getReviewNo());
		User user = new User();
		user.setUserId(request.getId());
		entity.setUser(user);
		entity.setReviewTitle(request.getReviewTitle());
		entity.setRegion(request.getRegion());
		entity.setPeriod(request.getPeriod());
		entity.setReviewContent(request.getReviewContent());
		entity.setGid(request.getGid());
		
		return entity;
	}
	
	
	//(공통) entity -> 커맨드(단일)
	private ReviewRequest entityToRequest(ReviewEntity entity) {
		ReviewRequest reviewRequest = new ReviewRequest();
		reviewRequest.setReviewNo(entity.getReviewNo());
		reviewRequest.setName(entity.getUser().getUserNm());
		reviewRequest.setId(entity.getUser().getUserId());
		reviewRequest.setReviewTitle(entity.getReviewTitle());
		reviewRequest.setRegion(entity.getRegion());
		reviewRequest.setPeriod(entity.getPeriod());
		reviewRequest.setReviewContent(entity.getReviewContent());
		reviewRequest.setGid(entity.getGid());
		reviewRequest.setReviewRead(entity.getReviewRead());
		reviewRequest.setRegDt(entity.getRegDt());
		reviewRequest.setModDt(entity.getModDt());
		
		return reviewRequest;
	}

	//(공통) entity -> 커맨드(List)
	private List<ReviewRequest> entityToRequest(List<ReviewEntity> lists){
		List<ReviewRequest> requestLists = new ArrayList<>();
		for(ReviewEntity entity : lists) {
			ReviewRequest request = new ReviewRequest();
			request = entityToRequest(entity);
			requestLists.add(request);
		}
		
		return requestLists;
	}
	
	
	// 여행후기 모든 목록 조회
	public List<ReviewRequest> getAllReviewList() {		
		
		List<ReviewEntity> lists = reviewRepository.findAll(Sort.by(Direction.DESC, "reviewNo"));
		if(lists.size()==0) {
			return null;
		}
			List<ReviewRequest> requestLists = entityToRequest(lists);
			return requestLists;
	}
	
	// 한 가지 목록 조회
	public ReviewRequest getOneReviewList(Long reviewNo) throws Exception{
		
		ReviewEntity entityList = reviewRepository.findByReviewNo(reviewNo).orElse(null);
		if(entityList==null) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		ReviewRequest requestList = entityToRequest(entityList); 	

		return requestList;
	}
	
	// 검색어로 조회
	public List<ReviewRequest> searchList(ReviewSearchRequest searchRequest) throws Exception{
		String keyword = searchRequest.getKeyword();
		/*Querydsl*/
		QReviewEntity qReview = QReviewEntity.reviewEntity; //Querydsl로 쿼리생성 위해 QReviewEntity객체 사용
		JPAQuery<ReviewEntity> query = jpaQueryFactory.selectFrom(qReview)
													.where(qReview.reviewTitle.contains(keyword)
													.or(qReview.region.like("%"+keyword+"%"))
													.or(qReview.reviewContent.contains(keyword)))
													.orderBy(qReview.reviewNo.desc());
		List<ReviewEntity> searchLists = query.fetch(); //조회결과 리스트 반환
		
		if(searchLists.size()==0) {
			throw new RuntimeException("조회결과가 없습니다.");
		}
		
		List<ReviewRequest> requestLists = entityToRequest(searchLists);

		return requestLists;
	}
	
	//후기 등록하기
	@Transactional
	public ReviewRequest registerReview(ReviewRequest reviewRequest) throws Exception{

		try {
			User user = userRepository.findByUserId(reviewRequest.getId()).orElse(null);
			if(user==null) {
				throw new RuntimeException("아이디가 존재하지 않습니다.");
			}
			ReviewEntity entity = requestToEntity(reviewRequest);
			entity.setUser(user);
			
			entity = reviewRepository.save(entity);
			
			ReviewRequest request = entityToRequest(entity); 
			
			return request;
			
		}catch(Exception e) {
			throw new RuntimeException("처리 도중 오류가 발생했습니다. 저장 실패");
		}		
	}
	
	//조회수 증가
	public boolean updateReviewRead(Long reviewNo) {
		int affectedRow = reviewRepository.updateReviewRead(reviewNo);
		return affectedRow > 0;
	}

	//게시글 삭제
	public boolean deleteReview(Long reviewNo) throws Exception{
		int affectedRow = reviewRepository.deleteByReviewNo(reviewNo);
		if(affectedRow==0) {
			throw new RuntimeException();
		}
		return affectedRow > 0;
	}

	//게시글 수정
	@Transactional
	public boolean updateReview(Long reviewNo, ReviewRequest reviewRequest) throws Exception{
		try {
			//user와 review 기존목록 가져오기
			User user = userRepository.findByUserId(reviewRequest.getId()).orElse(null);
			if(user==null) {
				throw new RuntimeException("아이디가 존재하지 않습니다.");
			}
			ReviewEntity revewEntity = reviewRepository.findByReviewNo(reviewNo).orElse(null);
			if(revewEntity==null) {
				throw new RuntimeException("게시글이 존재하지 않습니다.");
			}
			//새로운 내용 덮어쓰기
			revewEntity = requestToEntity(reviewRequest);
			revewEntity.setUser(user);
			//review로 가져온 값과 다르면 업데이트
			reviewRepository.save(revewEntity);
			
			return true;
			
		}catch(Exception e) {
			throw new RuntimeException("수정할 내용이 없습니다.");
		}
	}	 

}
