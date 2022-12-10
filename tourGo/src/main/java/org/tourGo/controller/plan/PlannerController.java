	package org.tourGo.controller.plan;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.models.plan.details.PlanDetails;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlanDetailsService;

import lombok.Value;

@Controller
public class PlannerController {

	@GetMapping("/plan")
	public String plannerDetails() {
		
		
		return "plan/planDetails";
	}
	@GetMapping("/makeplan")
	public String makePlan() {
		return "plan/makePlan";
	}
		
		
}
