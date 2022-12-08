package org.tourGo.controller.plan;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlanDetailsService;

import lombok.Value;

@Controller
public class PlannerController {


		
	@GetMapping("/plan")
	public String plannerDetails() {
	/**	entity.setName("테스트");
		entity.setAdd("서울특별시");
		entity.setSTime(LocalTime.now());
		entity.setETime(LocalTime.now().plusHours(10L));
		entity.setDay(7);
		entity.setImage("C:\\juj\\ProjectA\\tourGo\\src\\main\\resources\\static\\images\\test.jpg");
	*/
		
		
		return "plan/planDetails";
	}
	@GetMapping("/makeplan")
	public String makePlan() {
		return "plan/makePlan";
	}
		
		
}
