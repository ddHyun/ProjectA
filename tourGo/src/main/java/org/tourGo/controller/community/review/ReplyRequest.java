package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.tourGo.models.entity.community.review.ReplyEntity;

import lombok.*;
/*
 * 댓글
 * */
@Getter @Setter
public class ReplyRequest {

	private Long replyNo;			//식별자
	private Long reviewNo;			//게시글번호
	private String id;					//작성자id
	private String name;				//작성자명
	@NotBlank(message="내용을 입력해주세요")
	private String replyContent;	//내용
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;//수정일
	private int depth;					
	private Long idParent;			//모댓글의 글번호
	private String listOrder;			//1차정렬
	private String deleteYn;			//삭제여부
	
	
	public ReplyRequest() {	}

	public ReplyRequest(ReplyEntity entity) {
		replyNo = entity.getReplyNo();
		reviewNo = entity.getReview().getReviewNo();
		id = entity.getUser().getUserId();
		name = entity.getUser().getUserNm();
		replyContent = entity.getReplyContent();
		regDt = entity.getRegDt();
		modDt = entity.getModDt();
		depth = entity.getDepth();
		idParent = entity.getIdParent();
		listOrder = entity.getListOrder();
		deleteYn = entity.getDeleteYn();
	}

	@Override
	public String toString() {
		return "ReplyRequest [replyNo=" + replyNo + ", replyContent=" + replyContent + ", regDt=" + regDt + ", modDt="
				+ modDt + ", depth=" + depth + ", idParent=" + idParent + ", listOrder=" + listOrder + "]";
	}
	
	
}
