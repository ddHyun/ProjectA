	package org.tourGo.controller.plan;



import java.security.Principal;
import java.time.LocalDate;
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
	
	@GetMapping() // 여행 상세 일정 보는 화면
	public String plannerdate(Model model/* ,@AuthenticationPrincipal PrincipalDetail principal */) {
		/*
		 * //,@AuthenticationPrincipal PrincipalDetail principal try { Optional<User>
		 * _user = userRepository.findByUserId(principal.getUser().getUserId()); User
		 * user = _user.orElse(null);
		 * 
		 * List<Planner> list = plannerService.userPlanner(user.getUserNo());
		 * System.out.println(list); if(list != null || !list.isEmpty()) {
		 * model.addAttribute("list",list); } }catch(Exception e) { return
		 * "plan/plannerView"; }
		 */
		
		
		
		/**System.out.println("테스트용!!~~~~~~~~~");
		System.out.println(principal.getUser());
		System.out.println(principal.getUser().getUserId());*/

		
		/**Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.get();*/
		
	
		//model.addAttribute("user", user);
	model.addAttribute("addScript", "layer");
		return "plan/plannerView";
	}
	
	/*
	 * @PostMapping() public String planPs(PlannerRq plannerRq, PlanDetailsRq
	 * planDetailsRq,Model model) {
	 * 
	 * return null; }
	 */


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
		
		Planner planner = plannerService.getPlanner(no);
		PlanDetailsRq planDetailsRq = new PlanDetailsRq();
		planDetailsRq.setDay(planner.getDay());
		
		model.addAttribute("planDetails", planDetailsRq);
		
		
		System.out.println(planner);
		
		return "plan/makeDetails";
	}
	
	
	
	@PostMapping("/makeDetails")
	public String makePlanPs(@Valid PlannerRq plannerRq, Errors errors,Model model,@AuthenticationPrincipal PrincipalDetail principal ) {

		if (errors.hasErrors()) {
			return "plan/makePlan";
		}
	//history.back();
		Optional<User> _user = userRepository.findByUserId(principal.getUser().getUserId());
		User user = _user.orElse(null);
		
		Planner planner = plannerService.process(plannerRq, user);

		model.addAttribute("scripts", "parent.location.replace('../plan/makeDetails/" + planner.getPlannerNo() + "');");
		model.addAttribute("plannerRq", plannerRq);

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
