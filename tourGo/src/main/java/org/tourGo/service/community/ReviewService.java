package org.tourGo.service.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		ReviewEntity entity = null;
		int newHash = Objects.hash(request.getReviewTitle(), request.getReviewContent(), request.getRegion(), request.getPeriod());
		
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
			entity.setHash(newHash);
		} else { // 수정 
			int hash = entity.getHash();
			request.setSame(hash == newHash);
		}
		
		entity.setReviewTitle(request.getReviewTitle());
		entity.setRegion(request.getRegion());
		entity.setPeriod(request.getPeriod());
		entity.setReviewContent(request.getReviewContent());
		
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
		
		ReviewEntity entityList = reviewRepository.findById(reviewNo).orElse(null);
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
			throw new RuntimeException("처리 도중 오류가 발생했습니다. 삭제 실패");
		}
		return affectedRow > 0;
	}

	//게시글 수정
	@Transactional
	public boolean updateReview(Long reviewNo, ReviewRequest reviewRequest) {
			//review 기존목록 가져오기			
			ReviewEntity revewEntity = reviewRepository.findById(reviewNo).orElse(null);
			if(revewEntity==null) {
				throw new RuntimeException("게시글이 존재하지 않습니다.");
			}				
						
			//새로운 내용 덮어쓰기
			revewEntity = requestToEntity(reviewRequest);
			if (!reviewRequest.isSame()) {
				//review로 가져온 값과 다르면 업데이트
				reviewRepository.save(revewEntity);
				System.out.println("변경된 내용이 있음");
				
				return true;
			}
						
			return false;
	}
}
