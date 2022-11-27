package org.tourGo.service.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.controller.community.review.ReviewSearchRequest;
import org.tourGo.models.community.review.ReviewDao;
import org.tourGo.models.community.review.ReviewDto;
import org.tourGo.models.community.review.ReviewEntity;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.community.review.UserTestRepository;
import org.tourGo.models.community.review.User_test;

import lombok.RequiredArgsConstructor;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ReviewEntityRepository rRepository;
	@Autowired
	private UserTestRepository uRepository;
	
	//(공통) entity -> dto -> review커맨드로 이동
	public List<ReviewRequest> entityToRequest(List<ReviewEntity> lists){
		
		//entity -> dto
		List<ReviewDto> dtoLists = new ArrayList<>();
		for(ReviewEntity entity : lists) {
			ReviewDto dto = ReviewDto.toDto(entity);
			dtoLists.add(dto);
		}
		
		//dto -> review커맨드
		List<ReviewRequest> requestLists = new ArrayList<>();
		for(ReviewDto dto : dtoLists) {
			ReviewRequest request = ReviewRequest.toRequest(dto);
			requestLists.add(request);
		}
		
		return requestLists;
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
		//entity -> dto -> request
		ReviewEntity entityList = rRepository.findByReviewNo(reviewNo);
		ReviewDto dtoList = ReviewDto.toDto(entityList);
		ReviewRequest requestList = ReviewRequest.toRequest(dtoList); 	

		return requestList;
	}
	
	// 검색어로 조회
	public List<ReviewRequest> searchList(ReviewSearchRequest searchRequest){
		List<ReviewEntity> lists = rRepository.findByReviewTitleContaining(searchRequest.getKeyword());
		if(lists.size() > 0) {	//entity -> dto -> request
			List<ReviewRequest> requestLists = entityToRequest(lists);
			return requestLists;
		}else {
			return null;
		}
	}
	
	//후기 등록하기
	public boolean registerReview(ReviewRequest reviewRequest, List<MultipartFile> files) {
		for(MultipartFile file : files) {
			System.out.println("===============서비스단");
			System.out.println(file.getOriginalFilename());
		}
		return false;
	}

	// 페이징
	public Map<String, Integer> paging() {
		int page = 1; // 현재페이지
		int countPage = 10; // 한 화면에 담길 페이지 수(기본 10개)
		int totalCount = 23; // DB 총rowNum
		int pageSize = 10; // 한 페이지에 노출시킬 row 갯수
		int totalPage = totalCount / pageSize; // 탬플릿에 출력할 페이지 수

		if (page <= 0) { // 조회할 페이지가 1보다 작을 경우는 1로 고정
			page = 1;
		}
		if (totalPage < page) { // 조회할 페이지가 총페이지를 넘지 못하게 하기
			page = totalPage;
		}
		if (totalCount % pageSize != 0) {// 게시물 10개가 넘을 때마다 페이지 1 추가
			totalPage++;
		}

		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = startPage + countPage - 1;

		// 기본 10개 페이지번호 출력이지만 총페이지수가 적을 경우 총페이지수에 맞추기
		if (endPage >= totalPage) {
			endPage = totalPage;
		}

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);

		return pageMap;
	}	 

}
