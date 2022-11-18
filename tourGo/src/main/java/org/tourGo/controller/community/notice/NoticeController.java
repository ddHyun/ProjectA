package org.tourGo.controller.community.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class NoticeController {

	@GetMapping("/notice_main")
	public String index(Model model) {
		
		model.addAttribute("board", "notice");
		
		return "community/notice/notice_main";
	}
}
