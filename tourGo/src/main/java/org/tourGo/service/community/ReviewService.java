package org.tourGo.service.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.controller.community.review.ReviewRequest;
import org.tourGo.models.community.review.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	//여행후기 모든 목록 조회
	public List<ReviewRequest> getAllReviewList(){
		
		List<ReviewRequest> lists = new ArrayList<>();
		
		int totalCount = 23;
		
		for(int i=totalCount; i>=1; i--) {
			ReviewRequest reviewRequest = new ReviewRequest();
			reviewRequest.setReviewNo(i-1);
			reviewRequest.setName("사용자"+i);
			reviewRequest.setReviewTitle("제목"+i);
			reviewRequest.setRead(i+1);
			lists.add(reviewRequest);
		}
		
		return lists;
	}
	
	//페이징
	public Map<String, Integer> paging(){
		int page = 1; //현재페이지
		int countPage = 10; //한 화면에 담길 페이지 수(기본 10개)
		int totalCount = 23; //DB 총rowNum
		int pageSize = 10; //한 페이지에 노출시킬 row 갯수
		int totalPage = totalCount/pageSize; //탬플릿에 출력할 페이지 수
		
		if(page <= 0) { //조회할 페이지가 1보다 작을 경우는 1로 고정
			page = 1;
		}
		if(totalPage < page) { //조회할 페이지가 총페이지를 넘지 못하게 하기
			page = totalPage;
		}
		if(totalCount%pageSize != 0) {//게시물 10개가 넘을 때마다 페이지 1 추가
			totalPage++;
		}		
		
		int startPage = ((page-1)/10)*10 + 1;
		int endPage = startPage + countPage - 1;
		
		//기본 10개 페이지번호 출력이지만 총페이지수가 적을 경우 총페이지수에 맞추기
		if(endPage >= totalPage) {
			endPage = totalPage;
		}
		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		
		return pageMap;
	}

	//글번호로 내용조회
	public ReviewRequest getOneReview(int reviewNo) {
		//ReviewDto reviewDto = new ReviewDto();
		//reviewDto.setReviewNo(reviewNo);
		//reviewDto.setId(id);
		//ReviewRequest reviewRequest = reviewDao.getOneReview(reviewNo, id);
		
		ReviewRequest reviewRequest = new ReviewRequest();
		reviewRequest.setReviewTitle("제목임");
		reviewRequest.setName("작성자임");
		reviewRequest.setReviewContent("내용임");
		reviewRequest.setRegion("여행지임");
		reviewRequest.setPeriod("기간임");
		reviewRequest.setReviewRegDt(LocalDateTime.now());
		
		return reviewRequest;
	}
}
