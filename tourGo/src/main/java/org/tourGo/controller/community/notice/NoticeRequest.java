package org.tourGo.controller.community.notice;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter @Setter
public class NoticeRequest {

	private Long noticeNo;
	
	@NotBlank
	private String noticeTitle;
	
	@NotBlank
	private String noticeContent;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate postStartDt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate postEndDt;
	
	private String gid;				//파일그룹id
	
	private LocalDateTime regDt;	//작성일
	
	private LocalDateTime modDt;	//수정일
	
	private Integer viewCount;			//조회수
	
	private String userId;
}
