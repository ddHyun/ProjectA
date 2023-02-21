package org.tourGo.restcontroller.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.destination.like.DestinationUidRequest;
import org.tourGo.service.destination.DestinationLikeService;
import org.tourGo.service.destination.DestinationUidEntityRepository;


@RestController
public class DestinationLikeController {

	@Autowired 
	private DestinationLikeService destinationLikeService;
	@Autowired
	private DestinationUidEntityRepository destinationUidEntityRepository;
	
	@GetMapping("/destinationlike")
	public JsonResult<Object> process(Long destinationNo,String uid , Model model,
			@AuthenticationPrincipal PrincipalDetail principal){

			int totalLikes = 0;
			String field = "liked";
			
			try {
				uid = uid == "" ? DestinationUidRequest.getUid(destinationNo,principal.getUser().getUserNo()) : uid;
				totalLikes = destinationLikeService.process(uid, field);
			}catch(Exception e) {
				throw new JsonException(e.getMessage());
			}
				JsonResult<Object> result = new JsonResult<>();
				result.setSuccess(true);
				result.setData(totalLikes);
				
				return result;
}
		

}