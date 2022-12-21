package org.tourGo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exam01Controller {
	@GetMapping("/ex01")
	public String test(Model model) {
		model.addAttribute("addScript", "layer");
		return "exam";
	}
	
	@GetMapping("/ex02")
	public String test2(Model model) {
		
		return "exam2";
	}
	
}
