package org.tourGo.controller.community.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tourGo.common.AlertException;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.community.query.QueryInfoService;

@Controller
public class QueryViewController {
	
	@Autowired
	private QueryInfoService service;
	
	@GetMapping("/community/query_view/{queryNo}")
	public String view(@PathVariable Long queryNo, Model model, Integer page,
									@AuthenticationPrincipal PrincipalDetail principal) {
		if(queryNo==null) {
			throw new AlertException("잘못된 접근입니다", "/community/query_main");
		}
		model.addAttribute("board", "query");
		model.addAttribute("addCss", new String[] {"community/community_common"});
		model.addAttribute("addScript", new String[] {"community/community_common"});
		
		page = page==null? 1: page;
		model.addAttribute("page", page);
		
		QueryRequest queryRequest = service.process(queryNo);
		
		User user = queryRequest.getUser();
		
		if(queryRequest.isSecretPost()==true) {//비밀글인데
			if(principal==null) {
				throw new AlertException("로그인 후 이용 가능합니다", "back");
			}else {
				Long userNo = principal.getUser().getUserNo();
				if(!user.getUserNo().equals(userNo)) {//작성자 본인이 아닌경우
					throw new AlertException("비밀글은 본인만 조회가 가능합니다.", "back");
				}
			}
		}
		
		//비회원일 때 id에 비번넣기
		String sessionUserId = "";
		if(principal!=null) {
			sessionUserId = principal.getUser().getUserId();
			model.addAttribute("sessionUserId", sessionUserId);
		}
		
		model.addAttribute("queryRequest", queryRequest);
		
		return "community/query/query_view";
	}
}
