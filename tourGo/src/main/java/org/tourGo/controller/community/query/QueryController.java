package org.tourGo.controller.community.query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 커뮤니티/문의사항
 * */

@Controller
@RequestMapping("/community")
public class QueryController {

	String baseUrl = "community/query/";
	
	//static & board명 추가
	private void addCssJs(String boardName, String[] cssList, String[] jsList, Model model) {
		model.addAttribute("board", boardName);
		model.addAttribute("addCss", cssList);
		model.addAttribute("addScript", jsList);
	}
	
	@GetMapping("/query_main")
	public String index(Model model) {
		addCssJs("query", new String[] {"community/community_common"}, 
					new String[] {"community/community_common"}, model);
		return baseUrl + "query_main";
	}
}
