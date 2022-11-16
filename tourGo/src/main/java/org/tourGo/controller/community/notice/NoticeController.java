package org.tourGo.controller.community.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

	@GetMapping("/community/notice/notice_main")
	public String index() {
		return "community/notice/notice_main";
	}
}
