package org.tourGo.controller.community.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.service.community.query.QueryListService;

/*
 * 커뮤니티/문의사항
 */

@Controller
public class QueryIndexController {

	@Autowired
	private QueryListService service;
	@Autowired
	private QueryEntityRepository queryRepository;
	
	@GetMapping("/community/query_main")
	public String index(Model model) {
		
		model.addAttribute("board", "query");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"community/community_common"});
		
		List<QueryRequest> lists = queryRepository.findAll().stream().map(QueryRequest::new).toList();
		model.addAttribute("lists", lists);
	
		return "community/query/query_main";
	}
}
