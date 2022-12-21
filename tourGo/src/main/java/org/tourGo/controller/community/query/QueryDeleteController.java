package org.tourGo.controller.community.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.common.AlertException;
import org.tourGo.service.community.query.QueryDeleteService;

@Controller
public class QueryDeleteController {

	@Autowired
	private QueryDeleteService service;
	
	@RequestMapping("/community/query_delete/{queryNo}")
	public String process(@PathVariable Long queryNo) {
		if(queryNo==null) {
			throw new AlertException("잘못된 접근입니다", "back");
		}
		
		service.process(queryNo);
		
		return "redirect:/community/query_main";
	}
}
