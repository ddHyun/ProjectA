package org.tourGo.controller.plan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateController {

	@GetMapping("/date") // 여행 상세 일정 보는 화면
	public String plannerdate() {
		return "plan/dateView";
	}

	@GetMapping("/makedate") // 여행 상세 일정 만드는 화면
	public String makeDate() {
		return "plan/makeDateView";
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/makedate2")
	public String makeDate2() {
		return "plan/makeDate";
	}
		@GetMapping("/readdate")
		public String reddate() {
			return "plan/read";
		}
		@GetMapping("/writedate")
		public String writedate() {
			return "plan/write";
		}
			
		
}
	
