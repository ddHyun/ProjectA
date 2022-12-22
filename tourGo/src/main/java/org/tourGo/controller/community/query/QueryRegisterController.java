package org.tourGo.controller.community.query;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourGo.common.AlertException;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.community.query.QuerySaveService;

@Controller
@RequestMapping("/community/query_register")
public class QueryRegisterController {

	@Autowired
	private QuerySaveService service;
	
	//static & board명 추가
	private void addCommons(Model model) {
		model.addAttribute("board", "query");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"community/community_common"});
	}
	
	@GetMapping
	public String form(Model model, @AuthenticationPrincipal PrincipalDetail principal) {
		//static & board명 추가
		addCommons(model);
		if(principal==null) {
			throw new AlertException("로그인 후 이용 가능합니다", "/user/login");
		}
		QueryRequest queryRequest = new QueryRequest();
		if(principal!=null) {
			User user = principal.getUser();
			queryRequest.setUser(user);
		}

		model.addAttribute("queryRequest", queryRequest);
		
		return "community/query/query_register";
	}
	
	@PostMapping
	public String process(@Valid QueryRequest queryRequest, Errors errors, Model model) {
		if(errors.hasErrors()) {
			//static & board명 추가
			addCommons(model);
			model.addAttribute("queryRequest", queryRequest);
			return "community/query/query_register";
		}
		
		queryRequest = service.process(queryRequest);
		model.addAttribute("queryRequest", queryRequest);
		
		return "redirect:/community/query_view/"+queryRequest.getQueryNo();
	}
}
