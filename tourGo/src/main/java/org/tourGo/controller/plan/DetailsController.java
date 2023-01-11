package org.tourGo.controller.plan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;

@Controller
public class DetailsController {
	
	@ResponseBody
	@GetMapping("select/{day}")
	public JsonResult<?> selectDay(@PathVariable String day){
	System.out.println(day);
	return new JsonResult<>(true, "성공", null);
	
	}
}