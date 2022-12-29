package org.tourGo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
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
	
	@GetMapping("/admin/board/reportList")
	public String reportList(Model model,
										@PageableDefault Pageable pageable,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										AdminSearchRequest searchRequest) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/demo/chart-area-demo", "admin/demo/chart-pie-demo"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		Page<Report> list = reportService.reportList(pageable, searchRequest);
		Pagination<Report> pagination = new Pagination<>(list, base_url + "/board/reportList");
		
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/board/reportList";
	}
	
}
