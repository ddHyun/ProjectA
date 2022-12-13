	package org.tourGo.controller.plan;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.PlanDetails.PlanDetailsBuilder;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlanDetailsService;

import lombok.Value;

@Controller
public class PlannerController {

	@Autowired
	private PlanDetailsRepository rp;
	@GetMapping("/plan")
	public String plannerDetails() {
		
		
		return "plan/planDetails";
	}
	@GetMapping("/makeplan")
	public String makePlan(Model model) {
		PlanDetailsRq rq = new PlanDetailsRq();
		model.addAttribute("planDetails",rq);
		return "plan/makePlan";
	}

			
	@PostMapping("/makePlan")
	public String makePlan22(Model model) {
		PlanDetailsRq rq = new PlanDetailsRq();
		rq = ((PlanDetailsRq)model.getAttribute("planDetails"));
		PlanDetails entity = PlanDetails.builder().address(rq.getAddress()).build();
		rp.save(entity);
		return "plan/makePlan";
	}
		
	
	@GetMapping("/makeplan2")
	public String makePlan2(Model model) {
		PlanDetailsRq rq = new PlanDetailsRq();
		model.addAttribute("planDetails",rq);
		return "plan/makePlanTest";

	}
		
}
