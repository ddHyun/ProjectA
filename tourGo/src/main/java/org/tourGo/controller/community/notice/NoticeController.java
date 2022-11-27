package org.tourGo.controller.community.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.community.NoticeService;

@Controller
@RequestMapping("/community")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
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

	//공지사항 메인페이지
	@GetMapping("/notice_main")
	public String index(NoticeRequest noticeRequest, Model model) {
		//css, js 추가
		Map<String, String[]> pathMap = getFileLists();
		model.addAttribute("board", "notice");
		model.addAttribute("addCss", pathMap.get("addCss"));
		model.addAttribute("addScript", pathMap.get("addScript"));
		
		//전체목록 조회
		List<NoticeRequest> lists = noticeService.getAllLists();
		model.addAttribute("lists", lists);
		
		return "community/notice/notice_main";
	}
	
	//공지사항 내용보기
	@GetMapping("/notice_read/noticeNo_{noticeNo}")
	public String readNotice(@PathVariable int noticeNo, Model model) {
	//css, js 추가
			Map<String, String[]> pathMap = getFileLists();
			model.addAttribute("board", "notice");
			model.addAttribute("addCss", pathMap.get("addCss"));
			model.addAttribute("addScript", pathMap.get("addScript"));
			
		return "community/notice/notice_read";
	}
}
