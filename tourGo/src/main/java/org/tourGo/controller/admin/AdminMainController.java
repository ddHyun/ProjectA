package org.tourGo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {

	@GetMapping("/admin_main")
	public String admin_main() {
		return  "admin_main";
	}
}
