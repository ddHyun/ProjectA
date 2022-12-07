package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.*;
/*
 * 댓글
 * */
@Getter @Setter
public class ReplyRequest {

	private Long replyNo;			//식별자
	@NotBlank
	private Long reviewNo;			//게시글번호
	@NotBlank
	private String id;					//작성자id
	@NotBlank
	private String name;				//작성자명
	@NotBlank
	private String replyContent;	//내용
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;//수정일
}
