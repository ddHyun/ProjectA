package org.tourGo.models.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportType {
	ABUSING("욕설 신고", 1),
	ADVERTISING("광고성 게시물 신고", 2),
	ETC("기타", 3);
	
	private final String option;
	private final int seq;
}
