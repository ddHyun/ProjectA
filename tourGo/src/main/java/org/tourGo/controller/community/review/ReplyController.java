package org.tourGo.controller.community.review;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonResult;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@PostMapping("/register")
	public JsonResult<ReplyRequest> register(){
		return null;
	}
}
