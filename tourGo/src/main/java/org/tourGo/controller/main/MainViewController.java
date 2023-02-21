package org.tourGo.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainViewController {

	@GetMapping("/main_view")
	public String main_view(Model model) {
		
		return "main/main_view";
	}
	
	@GetMapping("/main_view2")
	public String main_view2(Model model) {
		
		return "main/main_view2";
	}
	
	@GetMapping("/main_view3")
	public String main_view3(Model model) {
		
		return "main/main_view3";
	}
	
}
