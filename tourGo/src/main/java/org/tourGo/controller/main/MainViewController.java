package org.tourGo.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainViewController {

	@GetMapping("/main_view")
	public String main_view(Model model) {
		
		model.addAttribute("destination","전체");
		
		return "main/main_view";
	}
	
}
