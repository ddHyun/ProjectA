package org.tourGo.controller.community.notice;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.service.community.notice.NoticeService;

@Controller
@RequestMapping("/community")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	private String baseUrl = "community/notice/";
	
	//static 정보
	private void addCssJs(String boardName, String[] cssList, String[] jsList, Model model) {
		model.addAttribute("board", boardName);
		model.addAttribute("addCss", cssList);
		model.addAttribute("addScript", jsList);
	}
	

	//공지사항 메인페이지
	@GetMapping("/notice_main")
	public String index(NoticeRequest noticeRequest, Model model) {
		//css, js, board 추가
		addCssJs("notice", new String[] {"community/community_common"},
				new String[] {"community/community_common"}, model);
		
		//전체목록 조회
		List<NoticeRequest> lists = noticeService.getAllLists();
		model.addAttribute("lists", lists);		
		
		return baseUrl+ "notice_main";
	}
	
	//공지사항 내용보기
	@GetMapping("/notice_read/noticeNo_{noticeNo}")
	public String readNotice(@PathVariable int noticeNo,  Model model,
					@CookieValue(value="visitNotice", required=false)Cookie cookie) throws Exception{
	//css, js, board 추가
	addCssJs("notice", new String[] {"community/community_common"},
			new String[] {"community/community_common"}, model);
		
	/*쿠키 처리 S*/
	if(cookie!= null) {
		if(!cookie.getValue().contains("["+noticeNo+"]")) {
			cookie.setValue(cookie.getValue()+"_["+noticeNo+"]");
			response.addCookie(cookie);
			//조회수 증가메서드 추가
		}
	}else {
		Cookie newCookie = new Cookie("visitNotice", "["+noticeNo+"]");
		response.addCookie(newCookie);
		//조회수 증가메서드 추가
	}
	/*쿠키 처리 E*/
	
	//글번호로 내용 조회
	NoticeRequest noticeRequest = noticeService.getOneList(noticeNo);
	model.addAttribute("noticeRequest", noticeRequest);

	return baseUrl + "notice_read";
	}
}
