package org.tourGo.controller.community.query;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.tourGo.models.entity.community.query.QueryReplyEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
public class QueryReplyRequest {
	
	private Long queryReplyNo;
	
	@NotBlank
	private String queryReplyContent;
	
	private Long queryNo;
	
	private User user;
	
	private LocalDateTime regDt;
	
	private LocalDateTime modDt;
	
	@Builder
	public QueryReplyRequest(QueryReplyEntity entity) {
		queryReplyNo = entity.getQueryReplyNo();
		queryReplyContent = entity.getQueryReplyContent();
		queryNo = entity.getQuery().getQueryNo();
		user = entity.getUser();
		regDt = entity.getRegDt();
		modDt = entity.getModDt();
	}
	
}
