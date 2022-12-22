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
	
	public List<PlannerRq> userPlanner(User user){ //db로부터 플래너 번호를 내림차순하여 planner List형태로 변환하는 메서드


		
		List<Planner> list = plannerRepo.findAllByUser(user,Sort.by(Sort.Direction.DESC,"plannerNo"));
		List<PlannerRq> list2 = new ArrayList<>();
		
		for(Planner planner : list) {
		PlannerRq rq =	PlannerRq.builder().day(planner.getDay()).image(planner.getImage()).sdate(planner.getSdate()).edate(planner.getEdate())
		.memo(planner.getMemo()).planSize(planner.getPlanSize()).planType(planner.getPlanType()).plannerNo(planner.getPlannerNo())
		.title(planner.getTitle()).build();
		list2.add(rq);
		}
		
		return list2;
	}
	
	public Planner process(PlannerRq plannerRq,User user) {
		
		
		LocalDate sdate = (plannerRq.getSdate() == null)?LocalDate.now():plannerRq.getSdate();
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
	
	public PlannerRq toDto(Planner planner) {
		
		int day = planner.getDay();
		LocalDate sdate = planner.getSdate();
		LocalDate edate = sdate.plusDays(day);
		
		
		PlannerRq rq = PlannerRq.builder().day(day).sdate(sdate).edate(edate).image(null).memo(planner.getMemo()).planSize(planner.getPlanSize())
				.planType(planner.getPlanType()).title(planner.getTitle()).plannerNo(planner.getPlannerNo()).build();
		
		return rq;
	}
	
	public Planner toEntity(PlannerRq rq) {
		int day = rq.getDay();
		LocalDate sdate = rq.getSdate();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().day(day).sdate(sdate).edate(edate).image(null).memo(rq.getMemo()).planSize(rq.getPlanSize())
				.planType(rq.getPlanType()).title(rq.getTitle()).build();
		
		return planner;
		
	}
	
	
	
}
