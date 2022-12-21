package org.tourGo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminReportController {
	
	private String base_url = "/admin";
	
	@GetMapping("/admin/board/reportList")
	public String reportList(Model model,
										AdminSearchRequest searchRequest) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/demo/chart-area-demo", "admin/demo/chart-pie-demo"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		model.addAttribute("list", "list");
		
		return "admin/board/reportList";
	}
	
}
