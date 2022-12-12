package org.tourGo.service.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
		
		int newHash = Objects.hash(request.getReviewTitle(), request.getReviewContent(), request.getRegion(), request.getPeriod());
		
		// 후기 수정일 때는 게시글 번호(reviewNo)로 기존 영속성 조회
		if (request.getReviewNo() != null) {
			entity = reviewRepository.findById(request.getReviewNo()).orElse(null);
			System.out.println("커맨드-> 엔티티 / 기존 목록 가져옴");
		}
		
		if (entity == null) { // 후기 추가인 경우 새로운 entity 객체 생성 및 사용자 계정 생성, gid는 최초 추가시에만 생성 및 DB  처리
			entity = new ReviewEntity();
			User user = new User();
			user.setUserId(request.getId());
			entity.setUser(user);
			entity.setGid(request.getGid());
			entity.setHash(newHash);
			entity.setReviewTitle(request.getReviewTitle());
			entity.setRegion(request.getRegion());
			entity.setPeriod(request.getPeriod());
			entity.setReviewContent(request.getReviewContent());
			System.out.println("커맨드-> 엔티티 / 새거 생성함");
		} else { // 수정 
				int hash = entity.getHash();
				request.setSame(hash == newHash);			
		}
		
		return entity;		
	}
	
	
	//(공통) entity -> 커맨드(단일)
	public ReviewRequest entityToRequest(ReviewEntity entity) {
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
//	public Page<ReviewEntity> getAllReviewList() {		
//		
//		return getAllReviewList(1, 20, "read", null);
//	}
//
//	public Page<ReviewEntity> getAllReviewList(int page) {		
//		
//		return getAllReviewList(page, 20, "read", null);
//	}
//
//	public Page<ReviewEntity> getAllReviewList(int page, String order) {		
//		
//		return getAllReviewList(page, 20, order, null);
//	}
	
	
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
	
	
	
	
	
	
	
	
	
//	public Page<ReviewEntity> getAllReviewList(int page, int limit, String order, String keyword) {
//		limit = limit <= 0 ? 20 : limit;
//		BooleanBuilder builder = new BooleanBuilder();
//		if (keyword != null) {
//			QReviewEntity reviewEntity = QReviewEntity.reviewEntity;
//			builder.or(reviewEntity.reviewTitle.contains(keyword))
//					.or(reviewEntity.reviewContent.contains(keyword));
//		}
//		
//		String field = (order == null || !order.equals("read"))?"reviewNo":"reviewRead";
//		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Order.desc(field)));
//		
//		Page<ReviewEntity> lists = reviewRepository.findAll(builder, pageable);
//		
//		return lists;
//	}
//	
	// 한 가지 목록 조회
	public ReviewRequest getOneReviewList(Long reviewNo) throws Exception{
		
		ReviewEntity entityList = reviewRepository.findById(reviewNo).orElse(null);
		if(entityList==null) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		ReviewRequest requestList = entityToRequest(entityList); 	

		return requestList;
	}
	
//	// 검색어로 조회
//	public List<ReviewRequest> searchList(String keyword, String order){
//		QReviewEntity qReview = QReviewEntity.reviewEntity;
//
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(qReview.reviewTitle.contains(keyword))
//					.or(qReview.reviewContent.contains(keyword))
//					.or(qReview.region.contains(keyword));
//		
//		List<ReviewEntity> searchLists = new ArrayList<>();
//		
//		if(order.equals("date")) {
//			searchLists = (List<ReviewEntity>)reviewRepository.findAll(builder, Sort.by(Direction.DESC, "reviewNo"));
//		}
//		if(order.equals("read")) {
//			searchLists = (List<ReviewEntity>)reviewRepository.findAll(builder, Sort.by(Direction.DESC, "reviewRead"));
//		}
//		
//		if(searchLists.size()==0) {
//			throw new RuntimeException("조회결과가 없습니다.");
//		}
//		
//		List<ReviewRequest> requestLists = entityToRequest(searchLists);
//
//		return requestLists;
//	}
	
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
			
			ReviewRequest request = entityToRequest(entity); 
			
			return request;
	}

	//게시글 수정
	@Transactional
	public boolean updateReview(Long reviewNo, ReviewRequest reviewRequest){

		//기존 내용이라 DB조회 후 영속성 안으로 가져옴
		ReviewEntity reviewEntity = requestToEntity(reviewRequest);			
		
		//변경사항 체크
		BiPredicate<String, String> checkUpdate = (s1, s2) -> s1.equals(s2);
		
		String[] oldData = {reviewEntity.getReviewTitle(), reviewEntity.getReviewContent(),
				reviewEntity.getPeriod(), reviewEntity.getRegion()};
		String[] newData = {reviewRequest.getReviewTitle(), reviewRequest.getReviewContent(),
										reviewRequest.getPeriod(), reviewRequest.getRegion()};
		boolean isCheck = true;		
		for(int i=0; i<oldData.length; i++) {
			boolean compare = checkUpdate.test(oldData[i], newData[i]);
			if(!compare) {
				isCheck = compare;
				break;
			}
		}
		
		
		if(!isCheck) {
			//checkUpdate 결과 false인 것들만 entity로 넣어주기
			reviewEntity.setReviewTitle(reviewRequest.getReviewTitle());
			reviewEntity.setReviewContent(reviewRequest.getReviewContent());
			reviewEntity.setRegion(reviewRequest.getRegion());
			reviewEntity.setPeriod(reviewRequest.getPeriod());
			reviewRepository.save(reviewEntity);
			return true;
		}
		
		return false;
	}	
}
