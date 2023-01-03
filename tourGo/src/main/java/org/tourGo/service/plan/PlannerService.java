package org.tourGo.service.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.tourGo.common.AlertException;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.plan.entity.QPlanner;
import org.tourGo.models.user.UserRepository;

import com.querydsl.core.BooleanBuilder;



@Service
public class PlannerService {

	@Autowired
	private PlannerRepository plannerRepo; // 여기서 선언해서 그런건가요?
	@Autowired
	private UserRepository userRepo;
	
	public List<PlannerRq> userPlanner(User user)	{ //db로부터 플래너 번호를 내림차순하여 planner List형태로 변환하는 메서드


		List<Planner> list = plannerRepo.findAllByUser(user,Sort.by(Sort.Direction.DESC,"plannerNo"));
		List<PlannerRq> list2 = new ArrayList<>();
		
		for(Planner planner : list) {
		PlannerRq rq =	PlannerService.toDto(planner);
		
		list2.add(rq);
		}
		
		return list2;
	}
	
	public Planner updatePlanner(PlannerRq request,User user) {
		
	
		Planner planner = PlannerService.toEntity(request, user);
		plannerRepo.save(planner);
		
		return planner;

	}
	public void deletePlanner(Planner planner) {
		
		plannerRepo.delete(planner);
		
	}
	
//	public List<Planner> plannerList(User user){
//		List<Planner> list = plannerRepo.findAllByUser(user,Sort.by(Sort.Direction.DESC,"plannerNo"));
//
//		
//		return list;
//	}
	
	
	public Page<Planner> plannerList(Pageable pageable,User user){
		BooleanBuilder builder = new BooleanBuilder();
		QPlanner planner = QPlanner.planner;
		builder.and(planner.user.eq(user));
		
		Page<Planner> list = plannerRepo.findAll(builder, pageable);
		
		
		
		return list;
	}
	
	
	public Page<Planner> plannerSearchList(String searchKeyword, Pageable pageable) {
		
		return plannerRepo.findByTitleContaining(searchKeyword, pageable); // plannerRepo 왜 이거인지??
	}
	
	
	
	
	public Planner insertPlanner(PlannerRq plannerRq,User user) {
		
		
		/**LocalDate sdate = (plannerRq.getSdate() == null)?LocalDate.now():plannerRq.getSdate();
		Integer day = plannerRq.getDay();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().title(plannerRq.getTitle()).day(day).sdate(sdate).edate(edate).memo(plannerRq.getMemo())
		.planSize(plannerRq.getPlanSize()).planType(plannerRq.getPlanType()).user(user).heart(plannerRq.getHeart())
		.hit(plannerRq.getHit()).image(plannerRq.getImage())
		.build();*/
		Planner planner = PlannerService.toEntity(plannerRq, user);
	
		planner = plannerRepo.save(planner);
		return planner;
		
	}
	
	public Planner getPlanner(Long id) {
		
		Planner planner = plannerRepo.findById(id).orElse(null);
		
		
		return planner;
	}
	
	public static PlannerRq toDto(Planner planner) {
		
		int day = planner.getDay();
		LocalDate sdate = planner.getSdate();
		LocalDate edate = sdate.plusDays(day);
		
		
		PlannerRq rq = PlannerRq.builder().day(day).sdate(sdate).edate(edate).image(planner.getImage()).memo(planner.getMemo()).planSize(planner.getPlanSize())
				.planType(planner.getPlanType()).title(planner.getTitle()).plannerNo(planner.getPlannerNo()).heart(planner.getHeart())
				.hit(planner.getHit()).open(planner.getOpen()).build();
		
		return rq;
	}
	
	public static Planner toEntity(PlannerRq rq,User user) {
		int day = rq.getDay();
		LocalDate sdate = rq.getSdate();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().plannerNo(rq.getPlannerNo()).day(day).sdate(sdate).edate(edate).image(rq.getImage()).memo(rq.getMemo()).planSize(rq.getPlanSize())
				.planType(rq.getPlanType()).title(rq.getTitle()).user(user).open(rq.getOpen()).build();
		
		return planner;
		
	}
	
	public static Planner toEntity(PlannerRq rq) {
		int day = rq.getDay();
		LocalDate sdate = rq.getSdate();
		LocalDate edate = sdate.plusDays(day);
		Planner planner = Planner.builder().plannerNo(rq.getPlannerNo()).day(day).sdate(sdate).edate(edate).image(rq.getImage()).memo(rq.getMemo()).planSize(rq.getPlanSize())
				.planType(rq.getPlanType()).title(rq.getTitle()).open(rq.getOpen()).build();
		
		return planner;
		
	}


	
	
	
}
