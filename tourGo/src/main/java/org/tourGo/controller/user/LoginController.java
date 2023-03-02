package org.tourGo.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.config.auth.PrincipalDetail;

@Controller
public class LoginController {
	
	private String fixed_url = "user/";
	
	@GetMapping("/user/login")
	public String login(@RequestParam(value="error", required=false) String error,
								@RequestParam(value="exception", required=false) String exception
								, Model model) {
		
		// 로그인 실패 시 error 표기 model로 보내줌
		model.addAttribute("addScript", new String[] {"user/login"});
		model.addAttribute("addCss", new String[] {"common"});
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return fixed_url + "login";
	}
	
	@PostMapping("/user/login")
	public String loginPs(Model model) {
		return fixed_url + "login";
	}
	
	@RequestMapping("/user/loginRedirect")
	public String loginPs(HttpServletRequest request
									, @AuthenticationPrincipal PrincipalDetail principal) {
		
		// System.out.println("로그인 사용자 : " + principal.getUsername());
		
		// 관리자 또는 전체 관리자인 경우 admin 인덱스 페이지로 이동
		if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
			return "redirect:/admin/index";
		}
		
		return "redirect:/main_view";
	}
}
