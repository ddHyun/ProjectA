package org.tourGo.controller.community.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	@Value("${community.addCss}")
	private String[] addCss;
	@Value("${community.addScript}")
	private String[] addScript;
	
	public Map<String, String[]> getFileLists(){
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
	public String readNotice(@PathVariable int noticeNo, Model model) throws Exception{
	//css, js 추가
			Map<String, String[]> pathMap = getFileLists();
			model.addAttribute("board", "notice");
			model.addAttribute("addCss", pathMap.get("addCss"));
			model.addAttribute("addScript", pathMap.get("addScript"));
			
			Cookie[] cookies1 = request.getCookies();
			if(cookies1 != null) {
				for(Cookie cookie : cookies1) {
					if(cookie.getName().equals("nReadHit")) {
						cookie.setMaxAge(0);
					}
				}
			}
			
			//글번호로 내용 조회
			NoticeRequest noticeRequest = noticeService.getOneList(noticeNo);
			model.addAttribute("noticeRequest", noticeRequest);

			//조회수 로직 : 글번호를 쿠키에 저장해 중복 유무 확인
			//조회수 쿠키 조회
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					System.out.printf("==========cookie.getName=%s, cookie.getValue=%s", cookie.getName(), cookie.getValue());
					
					//해당 쿠키가 없을 경우
					if(!cookie.getValue().contains(""+noticeNo)) {
						Cookie myCookie = new Cookie("detailReadHit", ""+noticeNo);
						myCookie.setMaxAge(60*2);
						response.addCookie(myCookie);
						noticeService.addReadCnt(noticeNo);
					}
				}
			}else { //쿠키가 null일 경우
				Cookie myCookie = new Cookie("detailReadHit", ""+noticeNo);
				myCookie.setMaxAge(60*2);
				response.addCookie(myCookie);
				noticeService.addReadCnt(noticeNo);
			}
			
		return "community/notice/notice_read";
	}
}
