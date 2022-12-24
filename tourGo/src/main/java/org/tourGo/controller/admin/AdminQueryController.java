package org.tourGo.controller.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.controller.community.query.QueryReplyRequest;
import org.tourGo.controller.community.query.QueryRequest;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.community.query.QueryReplyEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.admin.AdminQueryService;
import org.tourGo.service.community.query.QueryReplyService;
import org.tourGo.service.user.UserService;

@Controller
public class AdminQueryController {
	
	@Autowired
	private AdminQueryService queryService;
	
	@Autowired
	private QueryReplyService queryReplyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	private String base_url = "/admin";
	
	private void addCommons(Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
	}
	
	@GetMapping("/admin/query/queryList")
	public String queryList(Model model,
									@PageableDefault Pageable pageable,
									@RequestParam(name="searchType", required=false) String searchType,
									@RequestParam(name="searchKeyword", required=false) String searchKeyword,
									@RequestParam(name="page", required=false) String page,
									AdminSearchRequest searchRequest) {
		
		addCommons(model);
		Page<QueryEntity> list = queryService.queryList(pageable, searchRequest);
		Pagination<QueryEntity> pagination = new Pagination<>(list, base_url + "/board/queryList");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/query/queryList";
	}
	
	@GetMapping("/admin/query/queryView/{queryNo}")
	public String queryView(Model model,
										@PathVariable("queryNo") Long queryNo,
										@PageableDefault Pageable pageable,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										@CookieValue(value="visitQuery", required=false) Cookie cookie,
										QueryRequest queryRequest) {
		
		addCommons(model);
		model.addAttribute("addScript", new String[] {"admin/queryView"});
		QueryEntity query = queryService.findById(queryNo).orElse(null);
		queryRequest = new QueryRequest(query);
		
		/*쿠키 처리 S*/
		if(cookie != null) {
			if(!cookie.getValue().contains("["+queryNo+"]")) {
				cookie.setValue(cookie.getValue()+"_["+queryNo+"]");
				response.addCookie(cookie);
				//조회수 증가메서드 추가
				queryService.updateQueryRead(queryNo);
			}
		} else {
			Cookie newCookie = new Cookie("visitQuery", "["+queryNo+"]");
			response.addCookie(newCookie);
			//조회수 증가메서드 추가
			queryService.updateQueryRead(queryNo);
		}
		/*쿠키 처리 E*/
		
		// 답변 체크
		QueryReplyEntity queryReply = queryReplyService.findByQueryNo(queryNo).orElse(null);
		if(queryReply != null) {
			QueryReplyRequest queryReplyRequest = new QueryReplyRequest(queryReply);
			model.addAttribute("queryReplyRequest", queryReplyRequest);
		}
		
		model.addAttribute("queryRequest", queryRequest);
		model.addAttribute("page", page);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "admin/query/queryView";
	}
	
	@PostMapping("/admin/query/queryReplyRegister")
	public String queryReplyRegister(Model model,
										@AuthenticationPrincipal PrincipalDetail principal,
										QueryRequest queryRequest,
										@Valid QueryReplyRequest queryReplyRequest,
										AdminSearchRequest searchRequest,
										Errors errors) {
		
		User user = userService.findByUserId(principal.getUsername()).orElseThrow();
		
		QueryEntity query = queryService.findById(queryReplyRequest.getQueryNo()).orElse(null);
		queryRequest = new QueryRequest(query);
		
		if(errors.hasErrors()) {
			addCommons(model);
			
			model.addAttribute("queryRequest", queryRequest);
			return "admin/query/queryView";
		}
		
		try {
			queryReplyRequest.setUser(user);
			queryReplyService.registerQueryReply(queryReplyRequest);
			queryService.isSolvedSuccess(queryReplyRequest.getQueryNo());
		} catch(Exception e) {
			errors.reject("queryReplyError", e.getMessage());
			
			addCommons(model);
			model.addAttribute("queryRequest", queryRequest);
			
			return "admin/query/queryView";
		}
		
		return "redirect:/admin/query/queryList";
	}
}
