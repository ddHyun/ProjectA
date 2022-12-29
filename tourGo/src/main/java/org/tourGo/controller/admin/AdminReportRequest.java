package org.tourGo.controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.tourGo.models.entity.notice.Notice;
import org.tourGo.models.entity.report.Report;
import org.tourGo.models.report.ReportType;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class AdminReportRequest {

	private Long reportNo;
	
	private String reportTitle;
	
	private String reportContent;
	
	private LocalDateTime regDt;	//작성일
	
	private LocalDateTime modDt;	//수정일
	
	private String target;
	
	private Long targetId;
	
	private ReportType reportType;
	
	private char deleteYn;
	
	private String userId;
	
	@Builder
	public AdminReportRequest(Report report) {
		reportNo = report.getReportNo();
		reportTitle = report.getReportTitle();
		reportContent = report.getReportContent();
		target = report.getTarget();
		targetId = report.getTargetId();
		reportType = report.getReportType();
		deleteYn = report.getDeleteYn();
		userId = report.getUser().getUserId();
	}
}
