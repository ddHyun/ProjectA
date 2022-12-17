package org.tourGo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminNoticeController {
	
	@GetMapping("/admin/board/noticeList")
	public String noticeList(Model model) {
		
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/adminManage"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		return "admin/board/noticeList";
	}
	
}
