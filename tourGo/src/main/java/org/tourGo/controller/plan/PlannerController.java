	package org.tourGo.controller.plan;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.plan.entity.PlanDetails.PlanDetailsBuilder;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlanDetailsService;
import org.tourGo.service.plan.PlannerRepository;

import lombok.Value;

@Controller
public class PlannerController {

	@Autowired
	private PlanDetailsRepository detailsRepp;
	
	@Autowired
	private PlannerRepository plannerRepo;
	
	@GetMapping("/plan") // 여행 상세 일정 보는 화면
	public String plannerdate(Model model) {
		
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/plannerView";
	}

	@GetMapping("/makeplan") // 여행 상세 일정 만드는 화면
	public String makeDate() {
	
		return "plan/makeDateView";
	}
	
	@GetMapping("/makeplan2")
	public String makeDate2(Model model) {
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/makePlan";
	}
	
	@RequestMapping(value="/makeDetails", method=RequestMethod.GET)
	public String makePlan(Model model) {
	
		return "plan/makeDetails";
	}
	
	
	
	@PostMapping("/makeDetails")
	public String makePlanTest(@Valid PlannerRq plannerRq, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			return "plan/makePlan";
		}
		
		LocalDate sdate = plannerRq.getSdate();
		Integer _day = plannerRq.getDay();
		int day = _day == null ? 0 : _day;
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().title(plannerRq.getTitle()).day(day).sdate(sdate).edate(edate).memo(plannerRq.getMemo())
		.planSize(plannerRq.getPlanSize()).planType(plannerRq.getPlanType()).build();
		
		plannerRepo.save(planner);
		//'../plan/makePlan'
		model.addAttribute("scripts", "parent.location.replace('/makeDetails');");
		return "common/excution";
				
	}
	
		
	
	@GetMapping("/readplan")
	public String reddate() {
		return "plan/read";
	}
	@GetMapping("/writeplan")
	public String writedate() {
		return "plan/write";
	}
		
}
