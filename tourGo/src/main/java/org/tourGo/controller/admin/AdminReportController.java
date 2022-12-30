package org.tourGo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.controller.report.ReportRequest;
import org.tourGo.models.entity.notice.Notice;
import org.tourGo.models.entity.report.Report;
import org.tourGo.service.admin.AdminReportService;
import org.tourGo.service.user.UserService;

@Controller
public class AdminReportController {
	
	private String base_url = "/admin";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminReportService reportService;
	
	private void addCommons(Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
	}
	
	@GetMapping("/admin/board/reportList")
	public String reportList(Model model,
										@PageableDefault Pageable pageable,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										AdminSearchRequest searchRequest) {
		addCommons(model);
		Page<Report> list = reportService.reportList(pageable, searchRequest);
		Pagination<Report> pagination = new Pagination<>(list, base_url + "/board/reportList");
		
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/board/reportList";
	}
	
	@GetMapping("/admin/board/reportView/{reportNo}")
	public String reportView(Model model,
										@PathVariable("reportNo") Long reportNo,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										AdminReportRequest adminReportRequest) {
		
		addCommons(model);
		Report report = reportService.findById(reportNo).orElseThrow();
		
		adminReportRequest = new AdminReportRequest(report);
		
		model.addAttribute("adminReportRequest", adminReportRequest);
		model.addAttribute("page", page);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "admin/board/reportView";
	}
	
	// 후기 신고 대상 삭제
	@GetMapping("/admin/board/reportProcess/{reportNo}")
	public String deleteProcessReport(@PathVariable("reportNo") Long reportNo,
														@AuthenticationPrincipal PrincipalDetail principal,
														Model model) {
		
		reportService.deleteFromReportTarget(reportNo);
		
		return "redirect:/admin/board/reportList";
	}
}
