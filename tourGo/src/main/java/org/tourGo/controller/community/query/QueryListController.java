package org.tourGo.controller.community.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.service.community.query.QueryListService;

@Controller
public class QueryListController {

	@Autowired
	private QueryListService service;
	
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo-문의사항";
	}
	@GetMapping("/community/query_main")
	public String index(@RequestParam(name="page", required=false) Integer page, Model model) {
		
		model.addAttribute("board", "query");
		model.addAttribute("addCss", new String[] {"community/community_common", "community/pagination"});
		
		page = page == null ? 1 : page;
		Page<QueryEntity> results = service.gets(page, 10);
		Pagination<QueryEntity> pagination = new Pagination<>(results, "/community/query_main");
		List<QueryRequest> lists =results.getContent().stream().map(QueryRequest::new).toList();
		model.addAttribute("lists", lists);
		model.addAttribute("pagination", pagination);
		
		return "community/query/query_main";
	}
}
