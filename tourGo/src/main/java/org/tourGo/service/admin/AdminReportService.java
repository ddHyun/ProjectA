package org.tourGo.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.controller.admin.AdminSearchRequest;
import org.tourGo.models.entity.report.QReport;
import org.tourGo.models.entity.report.Report;
import org.tourGo.models.report.ReportRepository;
import org.tourGo.models.user.UserRepository;

import com.querydsl.core.BooleanBuilder;

@Service
public class AdminReportService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	public Optional<Report> findById(Long reportNo) {
		return reportRepository.findById(reportNo);
	}
	
	public Page<Report> reportList(Pageable pageable, AdminSearchRequest request) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QReport report = QReport.report;
		
		String searchType = request.getSearchType();
		String searchKeyword = request.getSearchKeyword();
		
		if(searchType != null) {
			// 1. 제목 검색
			if("title".equals(searchType)) {
				booleanBuilder.and(report.reportTitle.contains(searchKeyword));
			} 
			// 2. 내용 검색
			else if("content".equals(searchType)){
				booleanBuilder.and(report.reportContent.contains(searchKeyword));
			}
			// 3. 제목+내용 검색
			else if("title_content".equals(searchType)) {
				booleanBuilder.or(report.reportTitle.contains(searchKeyword))
										.or(report.reportContent.contains(searchKeyword));
			}
			// 4. 작성자 검색
			else if("userId".equals(searchType)) {
				booleanBuilder.and(report.user.userId.contains(searchKeyword));
			}
		}
		
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("regDt")));
		
		return reportRepository.findAll(booleanBuilder, pageable);
	}
}
