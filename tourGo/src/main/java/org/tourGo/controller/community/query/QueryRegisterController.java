package org.tourGo.controller.community.query;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.community.query.QuerySaveService;

@Controller
@RequestMapping("/community/query_register")
public class QueryRegisterController {

	@Autowired
	private UserRepository userRepository;
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
		
		QueryRequest queryRequest = new QueryRequest();
		User user = userRepository.findByUserId(principal.getUsername())
						.orElseThrow(()-> new AlertException("로그인 후 이용 가능합니다", "/user/login"));
		queryRequest.setUser(user);
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
		
		QueryEntity queryEntity = service.process(queryRequest);
		ModelMapper mapper = new ModelMapper();
		queryRequest = mapper.map(queryEntity, QueryRequest.class);
//		model.addAttribute("queryRequest", queryRequest);
		
		return "redirect:/community/query_main";
	}
}
