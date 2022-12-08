package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class ReviewRequest {

	private Long reviewNo; 		//글번호
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
	private boolean isSame; // 수정시에 지난 게시글과 동일성 여부
	
}
