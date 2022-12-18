	package org.tourGo.controller.plan;



import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.plan.entity.PlanDetails.PlanDetailsBuilder;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.plan.PlanDetailsRepository;
import org.tourGo.service.plan.PlanDetailsService;
import org.tourGo.service.plan.PlannerRepository;

import lombok.Value;

@Controller
@RequestMapping("/plan")
public class PlannerController {

	@Autowired
	private PlanDetailsRepository detailsRepp;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlannerRepository plannerRepo;
	
	@GetMapping() // 여행 상세 일정 보는 화면
	public String plannerdate(Model model) {
	
		/**System.out.println("테스트용!!~~~~~~~~~");
		System.out.println(principal.getUser());
		System.out.println(principal.getUser().getUserId());*/
		PlannerRq plannerRq= new PlannerRq();
		
		/**Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.get();*/
		
		model.addAttribute("plannerRq",plannerRq);
		//model.addAttribute("user", user);
		return "plan/plannerView";
	}
	
	@PostMapping()
	public String planPs(PlannerRq plannerRq, PlanDetailsRq planDetailsRq,Model model) {
		
		return null;
	}


	@GetMapping("/makePlan") // 여행 상세 일정 만드는 화면
	public String makeDate() {
	
		return "plan/makeDateView";
	}
	
	@GetMapping("/makeplan2")
	public String makeDate2(Model model) {
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/makePlan";
	}
	
	@GetMapping("/makeDetails/{no}")
	public String makePlan(Model model, @PathVariable Long no) {
		
		Planner planner = plannerRepo.findById(no).orElse(null);
		System.out.println(planner);
		
		return "plan/makeDetails";
	}
	
	
	
	@PostMapping("/makeDetails")
	public String makePlanTest(@Valid PlannerRq plannerRq, Errors errors,Model model,@AuthenticationPrincipal PrincipalDetail principal ) {

		if (errors.hasErrors()) {
			return "plan/makePlan";
		}
		//@AuthenticationPrincipal PrincipalDetail principal 
		Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.orElse(null);
		System.out.println(user.getUserId());
		LocalDate sdate = plannerRq.getSdate();
		Integer day = plannerRq.getDay();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().title(plannerRq.getTitle()).day(day).sdate(sdate).edate(edate).memo(plannerRq.getMemo())
		.planSize(plannerRq.getPlanSize()).planType(plannerRq.getPlanType()).user(user).build();
	
		planner = plannerRepo.save(planner);
		//'../plan/makePlan'
		model.addAttribute("scripts", "parent.location.replace('../plan/makeDetails/" + planner.getPlannerNo() + "');");
		model.addAttribute("plannerRq", plannerRq);
		model.addAttribute("planDetails", new PlanDetailsRq());
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
