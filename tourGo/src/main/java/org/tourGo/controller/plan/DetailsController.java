package org.tourGo.controller.plan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.details.PlanDetailsRq;

@Controller
public class DetailsController {
	
	@ResponseBody
	@GetMapping("select/{day}")
	public JsonResult<?> selectDay(Model model,@PathVariable String day){
	System.out.println(day);
	Integer ex = Integer.valueOf(day);
	
	PlanDetailsRq rq1 = new PlanDetailsRq((long) 1, 2L, null, null, "ex1", "ex1", 2, null, null, null, null);
	PlanDetailsRq rq2 = new PlanDetailsRq((long) 2, 3L, null, null, "ex2", "ex2", 2, null, null, null, null);
	return new JsonResult<>(true, "성공", null);
	
	}
}