package org.tourGo.service.report;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.controller.report.ReportRequest;
import org.tourGo.models.entity.report.Report;
import org.tourGo.models.report.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	// 신고 등록(수정은 없음)
	@Transactional
	public void process(ReportRequest request) {
		
		Report report = new Report(request);
		
		reportRepository.save(report);
	}
	
}
