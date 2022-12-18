package org.tourGo.controller.community.query;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class QueryRequest {

	private Long queryNo;			//게시글 번호
	@NotBlank
	private String queryTitle;		//게시글 제목
	@NotBlank
	private String queryContent;	//게시글 내용
	
	private User user;					//작성자 이름, 아이디
	
	private LocalDateTime regDt;	//작성일

	private int queryRead;			//조회수
	
	//엔티티 -> 커맨드
	public QueryRequest(QueryEntity entity) {
		queryNo = entity.getQueryNo();
		queryTitle = entity.getQueryTitle();
		queryContent = entity.getQueryContent();
		user = entity.getUser();
		regDt = entity.getRegDt();
		queryRead = entity.getQueryRead();
	}
}
