package org.tourGo.controller.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlannerRepository;

@Controller
public class DateController {
/**
	@Autowired
	private PlanDetailsRepository detailsRepo;
	
	@Autowired
	private PlannerRepository plannerRepo;
	
	
	@GetMapping("/date") // 여행 상세 일정 보는 화면
	public String plannerdate(Model model) {
		
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/dateView";
	}

	@GetMapping("/makedate") // 여행 상세 일정 만드는 화면
	public String makeDate() {
	
		return "plan/makeDateView";
	}
	@PostMapping("/makePlanTest")
	public String makePlanTest(@Valid PlannerRq plannerRq, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			return "plan/makeDate";
		}
		
		LocalDate sdate = plannerRq.getSdate();
		Integer _day = plannerRq.getDay();
		int day = _day == null ? 0 : _day;
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().title(plannerRq.getTitle()).day(day).sdate(sdate).edate(edate).memo(plannerRq.getMemo())
		.planSize(plannerRq.getPlanSize()).planType(plannerRq.getPlanType()).build();
		
		plannerRepo.save(planner);
		//'../plan/makePlan'
		model.addAttribute("scripts", "parent.location.replace('/makeplan');");
		return "common/excution";
				
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/makeplan2")
	public String makeDate2(Model model) {
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/makeDate";
	}
		@GetMapping("/readplan")
		public String reddate() {
			return "plan/read";
		}
		@GetMapping("/writeplan")
		public String writedate() {
			return "plan/write";
		}
			
		*/
}
	
