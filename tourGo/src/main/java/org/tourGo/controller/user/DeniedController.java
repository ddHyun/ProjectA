package org.tourGo.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeniedController {
	
	@RequestMapping("/error/denied")
	public String denied(HttpServletRequest request,
									Model model) {
		
		System.out.println("에러 페이지 이동!");
		model.addAttribute("msg", request.getAttribute("msg"));
		model.addAttribute("nextPage", request.getAttribute("nextPage"));
		
		return "common/denied";
	}
	
}
