package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.tourGo.models.community.review.ReviewDto;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Getter @Setter @ToString
public class ReviewRequest {

	private int reviewNo; 		//글번호
	private String id;			//아이디
	private String name;		//작성자
	@NotBlank
	private String reviewTitle;	//제목
	@NotBlank
	private String region;		//여행지
	@NotBlank
	private String period;		//기간
	@NotBlank
	private String reviewContent;	//내용
	private String gid;				//파일그룹id
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;	//수정일
	private int reviewRead;			//조회수
	
	//dto -> 커맨드로 바꾸기
	public static ReviewRequest dtoToRequest(ReviewDto dto) {
		
		ReviewRequest request = new ReviewRequest();
		
		request.setReviewNo(dto.getReviewNo());
		request.setId(dto.getUser().getUserId());
		request.setName(dto.getUser().getUserNm());
		request.setReviewTitle(dto.getReviewTitle());
		request.setRegion(dto.getRegion());
		request.setPeriod(dto.getPeriod());
		request.setReviewContent(dto.getReviewContent());
		request.setGid(dto.getGid());
		request.setRegDt(dto.getRegDt());
		request.setReviewRead(dto.getReviewRead());
		
		return request;
	}
	
	//커맨드 -> dto로 바꾸기
	public static ReviewDto requestToDto(ReviewRequest request) {
		ReviewDto dto = new ReviewDto();
		User user = new User(); 
		user.setUserId(request.getId());
		dto.setUser(user);
		dto.setGid(request.getGid());
		dto.setReviewTitle(request.getReviewTitle());
		dto.setRegion(request.getRegion());
		dto.setPeriod(request.getPeriod());
		dto.setReviewContent(request.getReviewContent());
		
		return dto;
	}
	
}
