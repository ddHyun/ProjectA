	package org.tourGo.controller.plan;



import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import org.tourGo.common.AlertException;
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
import org.tourGo.service.plan.PlannerService;

import lombok.Value;

@Controller
@RequestMapping("/plan")
public class PlannerController {

	@Autowired
	private PlanDetailsRepository detailsRepp;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlannerService plannerService;
	
	@Autowired
	private PlanValidator planValidator;
	
	@GetMapping() // 여행 상세 일정 보는 화면
	public String plan(Model model,@AuthenticationPrincipal PrincipalDetail principal) {
		model.addAttribute("addScript", "layer");

			try {
		Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.orElse(null);
		
		List<PlannerRq> list = plannerService.userPlanner(user);
		model.addAttribute("list",list);
		System.out.println(list);
		System.out.println(user);
			}catch(Exception e) {
			
				return "plan/plannerView";
			}

		return "plan/plannerView";
	}
	
	@GetMapping("/makeDetails/{no}")
	public String makeDetails(Model model, @PathVariable Long no) {
		try {
		Planner planner = plannerService.getPlanner(no);
	List<PlanDetailsRq> list = new ArrayList<>();
	for(PlanDetailsRq rq : list) {
		rq.setDay(planner.getDay());
	
	}
		PlannerRq plannerRq = plannerService.toDto(planner);
		
		model.addAttribute("planDetails", list);
		model.addAttribute("plannerRq", plannerRq);
		
		System.out.println(planner);
		}catch(Exception e) {
		
			
			model.addAttribute("scripts", " alert('유효하지않은 접근입니다'); location.replace('/plan');");
			return "common/excution";
			
			
		}
		return "plan/makeDetails";
	}
	
	  @PostMapping()
	  public String makeDetailsPs(PlannerRq plannerRq, PlanDetailsRq planDetailsRq,Model model) {
	  
		  
	  return null;
	  }
	
	@GetMapping("/makeplan2")
	public String makePlan(Model model) {
		PlannerRq plannerRq= new PlannerRq();
		model.addAttribute("plannerRq",plannerRq);
		return "plan/makePlan";
	}
	
	@PostMapping("/makeDetails")
	public String makePlanPs(@Valid PlannerRq plannerRq, Errors errors,Model model,@AuthenticationPrincipal PrincipalDetail principal ) {
	
		//planValidator.validate(plannerRq, errors);

		if (errors.hasErrors()) {
	
			return "plan/makePlan";
		}
	
		Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.orElse(null);
		
		Planner planner = plannerService.process(plannerRq, user);

		model.addAttribute("scripts", "parent.location.replace('../plan/makeDetails/" + planner.getPlannerNo() + "');");
		model.addAttribute("plannerRq", plannerRq);

		return "common/excution";
				
	}
	
	
	@GetMapping("/readplan")
	public String read() {
		return "plan/read";
	}
	@GetMapping("/writeplan")
	public String write() {
		return "plan/write";
	}
		
}
