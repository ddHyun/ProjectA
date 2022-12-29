package org.tourGo.controller.report;

import org.tourGo.models.entity.user.User;
import org.tourGo.models.report.ReportType;

import lombok.*;

@Getter @Setter @ToString
public class ReportRequest {
	
	private Long reportNo;
	
	private String reportTitle;
	
	private String reportContent;
	
	private String target;
	
	private Long targetId;
	
	private ReportType reportType;
	
	private User user;
}
