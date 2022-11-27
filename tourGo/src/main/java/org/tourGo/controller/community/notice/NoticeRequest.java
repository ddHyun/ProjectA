package org.tourGo.controller.community.notice;

import java.time.LocalDateTime;

import lombok.*;

@Getter @Setter @ToString
public class NoticeRequest {

	private int noticeNo;
	private String name;
	private String title;
	private String noticeContent;
	private LocalDateTime regDt;
	private int noticeRead;
}
