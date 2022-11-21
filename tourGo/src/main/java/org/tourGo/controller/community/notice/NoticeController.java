package org.tourGo.controller.community.notice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class NoticeController {
	
	@Value("${community.addCss}")
	private String[] addCss;
	@Value("${community.addScript}")
	private String[] addScript;
	
	private Map<String, String[]> getFileLists(){
		Map<String, String[]> pathMap = new HashMap<>();
		pathMap.put("addCss", addCss);
		pathMap.put("addScript", addScript);
		
		return pathMap;
	}

	@GetMapping("/notice_main")
	public String index(Model model) {
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		
		model.addAttribute("board", "notice");
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		return "community/notice/notice_main";
	}
}
