package org.tourGo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/index")
	public String main_index() {
		System.out.println("메인 페이지로 이동!!");
		return "index";
	}
	
	@GetMapping("/admin/index")
	public String admin_main(Model model) {
		model.addAttribute("addCss", new String[] {"vendor/fontawesome-free/css/all.min"});
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2.min"});
		model.addAttribute("addScript", new String[] {"admin/sb-admin-2.min"});
		// model.addAttribute("addScript", new String[] {"admin/sb-admin-2.min"});
		return  "admin/index";
	}
}
