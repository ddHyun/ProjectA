package org.tourGo.controller.community.query;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.community.query.QueryInfoService;
import org.tourGo.service.community.query.QuerySaveService;

@Controller
@RequestMapping("/community/query_update")
public class QueryUpdateController {
	
	@Autowired
	private QueryInfoService infoservice;
	@Autowired
	private QuerySaveService saveService;
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo-문의사항";
	}

	@GetMapping("/{queryNo}")
	public String form(@PathVariable Long queryNo, Model model) {
		//static & board명 추가
		model.addAttribute("board", "query");
		model.addAttribute("addCss", new String[] {"community/community_common"});
				
		QueryRequest queryRequest = infoservice.process(queryNo);
		model.addAttribute("queryRequest", queryRequest);
		
		return "community/query/query_update";
	}
	
	@PostMapping
	public String process(@Valid QueryRequest queryRequest, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			//static & board명 추가
			model.addAttribute("board", "query");
			model.addAttribute("addCss", new String[] {"community/community_common"});
			return "community/query/query_update";
		}
		queryRequest = saveService.process(queryRequest);
		model.addAttribute("queryRequest", queryRequest);
		return "redirect:/community/query_view/"+queryRequest.getQueryNo();
	}
}
