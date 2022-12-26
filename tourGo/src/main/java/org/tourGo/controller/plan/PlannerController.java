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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tourGo.common.AlertException;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.TourType;
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
	private UserRepository userRepository;
	@Autowired
	private PlannerService plannerService;
	
	@Autowired
	private PlanDetailsService detailsService;
	
	@ModelAttribute("planTypes")
	public TourType[] tourType() {
		return TourType.values();	
	}
	
	
	
	
	
	
	@GetMapping() // 여행 상세 일정 보는 화면
	public String plan(Model model,@AuthenticationPrincipal PrincipalDetail principal) {
		
	Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
	User user = _user.orElse(null);
	
	List<PlannerRq> list = plannerService.userPlanner(user);
	
	System.out.println(list);
		
		model.addAttribute("list", list);
		model.addAttribute("user", user);
	model.addAttribute("addScript", "layer");	
	return "plan/plannerView";
	}
	//아이디값확인해서 아이디일치하지않으면 예외처리하기!!
	@GetMapping("/makeDetails/{no}")
	public String makeDetails(Model model, @PathVariable Long no,@AuthenticationPrincipal PrincipalDetail principal) {
		try {
			Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
			User user = _user.orElse(null);
			
		Planner planner = plannerService.getPlanner(no);
		
		boolean check=	detailsService.checkPlanner(user, planner);
		if(!check) {
			model.addAttribute("scripts", " alert('유효하지않은 접근입니다'); location.replace('/plan');");
			return "common/excution";
		}
		
		List<PlanDetailsRq> list = new ArrayList<>();
		
		PlannerRq plannerRq = plannerService.toDto(planner);
		ArrayList<String> test = new ArrayList<>();
		for(int i =1 ; i<= plannerRq.getDay() ; i++) {
			String d = "day"+i;
			test.add(d);
		}
		model.addAttribute("test", test);
		model.addAttribute("planDetails", list);
		model.addAttribute("plannerRq", plannerRq);
		
		System.out.println(planner);
		}catch(Exception e) {
			throw new AlertException("없는 플래너입니다!!","/plan");
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
			model.addAttribute("plannerRq", plannerRq);
			return "plan/makePlan";
		}
	
		Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.orElse(null);
		
		Planner planner = plannerService.process(plannerRq, user);

		model.addAttribute("scripts", "parent.location.replace('../plan/makeDetails/" + planner.getPlannerNo() + "');");
		model.addAttribute("plannerRq", plannerRq);

		return "common/excution";
				
	}
	
	
	
	@GetMapping("/readplan/{no}")
	public String read(Model model, @PathVariable Long no ) {
		
		Planner planner = plannerService.getPlanner(no);
		
		model.addAttribute("planner", planner);
		model.addAttribute("addScript", "layer");	
		return "plan/read";
	}
	@GetMapping("/writeplan/{no}")

	public String write(Model model, @PathVariable Long no ) {
		
		Planner planner = plannerService.getPlanner(no);
		model.addAttribute("planner", planner);
		
		model.addAttribute("addScript", "layer");
		return "plan/write";
	
	}
	


	@PostMapping("/readplan")

	public String writeps(PlannerRq plannerRq)	{
		return "plan/read";
		
		
	}
}
