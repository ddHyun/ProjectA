package org.tourGo.controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.tourGo.models.entity.notice.Notice;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class AdminNoticeRequest {

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
	
	public AdminNoticeRequest(Notice notice) {
		noticeNo = notice.getNoticeNo();
		userId = notice.getUser().getUserId();
		noticeTitle = notice.getNoticeTitle();
		noticeContent = notice.getNoticeContent();
		postStartDt = notice.getPostStartDt();
		postEndDt = notice.getPostEndDt();
		gid = notice.getGid();
		regDt = notice.getRegDt();
		modDt = notice.getModDt();
		viewCount = notice.getViewCount();
	}
}
