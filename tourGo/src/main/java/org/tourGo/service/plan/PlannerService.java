package org.tourGo.service.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.user.UserRepository;

@Service
public class PlannerService {

	@Autowired
	private PlannerRepository plannerRepo;
	@Autowired
	private UserRepository userRepo;
	
	public List<Planner> userPlanner(Long userNo){ //db로부터 플래너 번호를 내림차순하여 planner List형태로 변환하는 메서드


		
		List<Planner> list = plannerRepo.findAllByUser(userNo,Sort.by(Sort.Direction.DESC,"plannerNo"));

		return list;
	}
	
	public Planner process(PlannerRq plannerRq,User user) {
		
		
		
		LocalDate sdate = plannerRq.getSdate();
		Integer day = plannerRq.getDay();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().title(plannerRq.getTitle()).day(day).sdate(sdate).edate(edate).memo(plannerRq.getMemo())
		.planSize(plannerRq.getPlanSize()).planType(plannerRq.getPlanType()).user(user).build();
	
		planner = plannerRepo.save(planner);
		return planner;
		
	}
	
	public Planner getPlanner(Long id) {
		
		Planner planner = plannerRepo.findById(id).orElse(null);
		
		
		return planner;
	}
	
	
	
}
