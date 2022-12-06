package org.tourGo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
	
	@GetMapping("/index")
	public String main_index() {
		System.out.println("메인 페이지로 이동!!");
		return "index";
	}
	
	@GetMapping("/admin/index")
	public String index(Model model) {
		System.out.println("어드민 인덱스 페이지로 이동!!");
		return "admin/index";
	}
}
