package org.tourGo.controller.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.config.auth.PrincipalDetail;

@Controller
public class AdminMainController {
	
	@GetMapping("/index")
	public String main_index() {
		System.out.println("메인 페이지로 이동!!");
		return "index";
	}
	
	@GetMapping("/admin/index")
	public String admin_main(Model model,
											@AuthenticationPrincipal PrincipalDetail principal) {
		
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all"});
		
		// 부트스트랩 관련 JS 추가
		// model.addAttribute("addScript", new String[] {"admin/index"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "bootstrap/js/bootstrap.bundle.min", "jquery-easing/jquery.easing.min"});
		return  "admin/index";
	}
}
