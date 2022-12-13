package org.tourGo.controller.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlannerRepository;

@Controller
public class DateController {

	@Autowired
	private PlanDetailsRepository detailsRepo;
	
	@Autowired
	private PlannerRepository plannerRepo;
	
	
	@GetMapping("/date") // 여행 상세 일정 보는 화면
	public String plannerdate(Model model) {
		
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("planner",plannerRq);
		return "plan/dateView";
	}

	@GetMapping("/makedate") // 여행 상세 일정 만드는 화면
	public String makeDate(Model model) {
	PlannerRq plannerRq= new PlannerRq();
	model.addAttribute("planner",plannerRq);
		return "plan/makeDateView";
	}
	@PostMapping("/makePlanTest")
	public String makePlanTest(Model model) {
		PlannerRq plannerRq=  (PlannerRq)model.getAttribute("planner");
		
		Planner planner = Planner.builder().title(plannerRq.getTitle()).build();
		
		plannerRepo.save(planner);
		
		return "plan/makePlan";
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/makedate2")
	public String makeDate2() {
		return "plan/makeDate";
	}
		@GetMapping("/readdate")
		public String reddate() {
			return "plan/read";
		}
		@GetMapping("/writedate")
		public String writedate() {
			return "plan/write";
		}
			
		
}
	
