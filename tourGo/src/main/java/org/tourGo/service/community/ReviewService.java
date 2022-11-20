package org.tourGo.service.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReviewDao;
import org.tourGo.models.community.review.ReviewDto;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	// 여행후기 모든 목록 조회
	public List<ReviewRequest> getAllReviewList() {
		
		List<ReviewDto> lists = reviewDao.getAllReviewList();
		//DTO에 담긴 데이터를 커맨드객체로 옮기기
		List<ReviewRequest> requestLists = new ArrayList<>(); 
		for(int i=lists.size()-1; i>=0; i--) {
			ReviewRequest request = new ReviewRequest();
			request.setReviewNo(lists.get(i).getReviewNo());
			request.setId(lists.get(i).getId());
			request.setName(lists.get(i).getId());
			request.setReviewTitle(lists.get(i).getReviewTitle());
			request.setReviewRegDt(lists.get(i).getReviewRegDt());
			request.setPeriod(lists.get(i).getPeriod());
			request.setRegion(lists.get(i).getRegion());
			request.setReviewContent(lists.get(i).getReviewContent());
			requestLists.add(request);
		}

		return requestLists;
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

	
	 //글번호로 내용조회 
	public ReviewRequest getOneReview(int reviewNo) { 
	
		ReviewDto reviewDto = reviewDao.getOneReview(reviewNo);
		ReviewRequest request = new ReviewRequest();
		request.setReviewNo(reviewDto.getReviewNo());
		request.setId(reviewDto.getId());
		request.setName(reviewDto.getId());
		request.setReviewTitle(reviewDto.getReviewTitle());
		request.setReviewRegDt(reviewDto.getReviewRegDt());
		request.setPeriod(reviewDto.getPeriod());
		request.setRegion(reviewDto.getRegion());
		request.setReviewContent(reviewDto.getReviewContent());
		
		return request;
	}
	 

}
