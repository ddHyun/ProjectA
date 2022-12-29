package org.tourGo.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.report.ReportType;
import org.tourGo.service.report.ReportService;
import org.tourGo.service.user.UserService;

@Controller
public class ReportController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;
	
	// 후기 신고
	@GetMapping("/report/review/{reviewNo}")
	public String reviewReport(@PathVariable Long reviewNo,
											@AuthenticationPrincipal PrincipalDetail principal,
											ReportRequest reportRequest,
											Model model) {
		
		model.addAttribute("addCss", new String[] {"report/report"});
		model.addAttribute("addScript", new String[] {"report/report", "fileUpload"});
		
		User user = userService.findByUserId(principal.getUsername()).orElseThrow();
		
		reportRequest.setTarget("review");
		reportRequest.setTargetId(reviewNo);
		reportRequest.setUser(user);
		
		model.addAttribute("reportRequest", reportRequest);
		model.addAttribute("reportType", ReportType.values());
		
		return "report/reportForm";
	}
	
	// 후기 신고 등록
	@PostMapping("/report/review/{reviewNo}")
	public String reviewReportPs(@PathVariable Long reviewNo,
												@AuthenticationPrincipal PrincipalDetail principal,
												ReportRequest reportRequest,
												Model model) {
		
		reportService.process(reportRequest);
		
		return "redirect:/community/review_main";
	}
	
	// 사용자 신고
	@GetMapping("/report/user/{userNo}")
	public String userReport(@PathVariable Long userNo,
										@AuthenticationPrincipal PrincipalDetail principal,
										ReportRequest reportRequest,
										Model model) {
		
		model.addAttribute("reportRequest", reportRequest);
		
		return "report/reportForm";
	}
	
}
