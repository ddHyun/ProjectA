package org.tourGo.models.entity.report;

import lombok.*;
import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.controller.report.ReportRequest;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.report.ReportType;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {
	
	@Id @GeneratedValue
	private Long reportNo;
	
	@Column(nullable=false, length=100)
	private String reportTitle;
	
	@Lob
	@Column(nullable=false)
	private String reportContent;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ReportType reportType;
	
	@Column(nullable=false)
	private String target;
	
	@Column(nullable=false)
	private Long targetId;
	
	@Column(nullable=false, columnDefinition="char(1) default 'N'", insertable=false)
	private char deleteYn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")
	private User user;
	
	@Builder
	public Report(ReportRequest request) {
		reportTitle = request.getReportTitle();
		reportContent = request.getReportContent();
		target = request.getTarget();
		targetId = request.getTargetId();
		reportType = request.getReportType();
		user = request.getUser();
	}
}
