package org.tourGo.controller.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.details.DetailsItems;
import org.tourGo.models.plan.details.PlanDetailsRq;

@Controller
public class DetailsController {
	
	
	@RequestMapping("select")
	public String selectDay(Model model,DetailsItems rqList){
	Integer day = rqList.getDay();
	System.out.println(rqList);
	ArrayList<PlanDetailsRq> list = new ArrayList<>();
	PlanDetailsRq rq1 = PlanDetailsRq.builder().detailsNo(10l).name("관광지3").address("주소1").day(1).build();
	PlanDetailsRq rq2 = PlanDetailsRq.builder().detailsNo(20l).name("관광지4").address("주소2").day(2).build();
	PlanDetailsRq rq3 = PlanDetailsRq.builder().detailsNo(20l).name("관광지5").address("주소3").day(3).build();
	PlanDetailsRq rq4 = PlanDetailsRq.builder().detailsNo(20l).name("관광지6").address("주소4").day(4).build();
	PlanDetailsRq rq5 = PlanDetailsRq.builder().detailsNo(20l).name("관광지7").address("주소6").day(5).build();
	list.add(rq2);
	list.add(rq1);
	list.add(rq3);
	list.add(rq4);
	list.add(rq5);
	
	list.removeIf(num->num.getDay()!=day);
	
	model.addAttribute("list", list);
	return "plan/makeDetails::#selected_items";
	
	}
	
	@ResponseBody
	@PostMapping("textxml")
	public JsonResult<?> testxml(String plannerNo, Model model){
		System.out.print(plannerNo);
		
		
		return new JsonResult<>(true, "성공", null);
	}
	
	
	
}