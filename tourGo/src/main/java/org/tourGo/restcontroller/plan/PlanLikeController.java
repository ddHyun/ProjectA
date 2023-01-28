package org.tourGo.restcontroller.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.plan.entity.like.PlanUidRequest;
import org.tourGo.service.plan.PlanLikeService;
import org.tourGo.service.plan.PlanUidEntityRepository;

@RestController
public class PlanLikeController {

	@Autowired 
	private PlanLikeService planLikeService;
	@Autowired
	private PlanUidEntityRepository planUidEntityRepository;
	
	@RequestMapping("/planlike")
	public JsonResult<Object> process(Long plannerNo,String uid , Model model,
			@AuthenticationPrincipal PrincipalDetail principal){

			int totalLikes = 0;
			String field = "liked";
			
			try {
				uid = uid == "" ? PlanUidRequest.getUid(plannerNo,principal.getUser().getUserNo()) : uid;
				totalLikes = planLikeService.process(uid, field);
			}catch(Exception e) {
				throw new JsonException(e.getMessage());
			}
				JsonResult<Object> result = new JsonResult<>();
				result.setSuccess(true);
				result.setData(totalLikes);
				
				return result;
}
		

}