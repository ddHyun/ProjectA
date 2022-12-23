package org.tourGo.controller.community.notice;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.models.entity.notice.Notice;
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
	
	//공지사항 메인페이지
	@GetMapping("/notice_main")
	public String index(Model model,
								@PageableDefault Pageable pageable,
								@RequestParam(name="searchType", required=false) String searchType,
								@RequestParam(name="searchKeyword", required=false) String searchKeyword,
								@RequestParam(name="page", required=false) String page,
								SearchRequest searchRequest,
								NoticeRequest noticeRequest) {
		//css, js, board 추가
		model.addAttribute("board", "notice");
		model.addAttribute("addCss", new String[] {"community/community_common", "community/pagination", "community/search"});
		
		//전체목록 조회
		Page<Notice> list = noticeService.noticeList(pageable, searchRequest);
		Pagination<Notice> pagination = new Pagination<>(list, "/notice_main");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return baseUrl+ "notice_main";
	}
	
	//공지사항 내용보기
	@GetMapping("/notice_read/noticeNo/{noticeNo}")
	public String readNotice(Model model, 
										@PathVariable Long noticeNo,  
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										@CookieValue(value="visitNotice", required=false) Cookie cookie) throws Exception{
	//css, js, board 추가
	model.addAttribute("board", "notice");
	model.addAttribute("addCss", new String[] {"community/community_common"});
		
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
	Notice notice = noticeService.findById(noticeNo).orElseThrow();
	model.addAttribute("notice", notice);

	return baseUrl + "notice_read";
	}
}
