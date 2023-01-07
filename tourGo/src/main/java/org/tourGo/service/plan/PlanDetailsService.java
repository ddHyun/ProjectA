package org.tourGo.service.plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.plan.entity.QPlanDetails;

import com.querydsl.core.BooleanBuilder;

@Service
public class PlanDetailsService {

	@Autowired
	private PlanDetailsRepository detailsRepo;
		
	@Autowired
	private PlannerService plannerService;
		
	public String getDetailsImage(Planner planner){
		
		//QPlanDetails qPlan = QPlanDetails.planDetails;
		
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc("day"));
		orders.add(Order.desc("sdate"));
		BooleanBuilder builder = new BooleanBuilder();
		QPlanDetails details = QPlanDetails.planDetails;
		builder.and(details.plannerNo.eq(planner));
		Sort sort = Sort.by(orders);
		
		List<PlanDetails> list = (List<PlanDetails>) detailsRepo.findAll(builder,sort);
		
		PlanDetails _details = list.get(0);
		String image = _details.getImage();
		return image;
	}
	
	public boolean checkPlanner(User user,Planner planner) {

		if(planner.getUser() != user) {
			return false;
		}
			
		return true;
	}
	public List<PlanDetailsRq> getPlanDetailsRqList(Planner planner){
		BooleanBuilder builder = new BooleanBuilder();
		QPlanDetails details = QPlanDetails.planDetails;
		builder.and(details.plannerNo.eq(planner));
		List<PlanDetailsRq> list = null;
		
		List<PlanDetails> _list = (List<PlanDetails>) detailsRepo.findAll(builder,Sort.by(Sort.Direction.ASC, "detailsNo"));
		
		for(PlanDetails entity : _list) {
			PlanDetailsRq rq = PlanDetailsService.toDto(entity);
			list.add(rq);
		}
		
		
		return list;
	}
	public List<PlanDetails> insertPlanDetails(List<PlanDetailsRq> _list,Planner planner){
		List<PlanDetails> list = null;
		
		for(PlanDetailsRq rq : _list) {
			PlanDetails details = PlanDetailsService.toEntity(rq,planner);
			details = detailsRepo.save(details);
			list.add(details);
		}
		
		
		
		return list;
		
	}
	
	
	
	   public static PlanDetailsRq toDto(PlanDetails entity) {

	    	return PlanDetailsRq.builder()
	    			.DetailsNo(entity.getDetailsNo())
	    			.plannerNo(entity.getPlannerNo().getPlannerNo())
	    			.stime(entity.getStime())
	    			.etime(entity.getEtime())
	    			.name(entity.getName())
	    			.address(entity.getAddress())
	    			.day(entity.getDay())
	    			.image(entity.getImage())
	    			.sigungu(entity.getSigungu())
	    			.mapX(entity.getMapX())
	    			.mapY(entity.getMapY())
	    			.build();
	    
	    }
	    
	    public static PlanDetails toEntity(PlanDetailsRq planDetails) {

	    	return PlanDetails.builder()
	    			.DetailsNo(planDetails.getDetailsNo())
	    			.stime(planDetails.getStime())
	    			.etime(planDetails.getEtime())
	    			.name(planDetails.getName())
	    			.address(planDetails.getAddress())
	    			.day(planDetails.getDay())
	    			.image(planDetails.getImage())
	    			.sigungu(planDetails.getSigungu())
	    			.mapX(planDetails.getMapX())
	    			.mapY(planDetails.getMapY())
	    			.build();
	    }
	    public static PlanDetails toEntity(PlanDetailsRq planDetails,Planner planner) {

	    	return PlanDetails.builder()
	    			.DetailsNo(planDetails.getDetailsNo())
	    			.plannerNo(planner)
	    			.stime(planDetails.getStime())
	    			.etime(planDetails.getEtime())
	    			.name(planDetails.getName())
	    			.address(planDetails.getAddress())
	    			.day(planDetails.getDay())
	    			.image(planDetails.getImage())
	    			.sigungu(planDetails.getSigungu())
	    			.mapX(planDetails.getMapX())
	    			.mapY(planDetails.getMapY())
	    			.build();
	    }
	    
	    public static List<PlanDetails> toEntityList(List<PlanDetailsRq> _list){
	    	List<PlanDetails> list = null;
	    	
	    	for(PlanDetailsRq rq : _list) {
	    		PlanDetails entity = PlanDetailsService.toEntity(rq);
	    		list.add(entity);
	    	}
	    	
	    	
	    	
			return list;
	    	
	    }
	    
	    public static List<PlanDetails> toEntityList(List<PlanDetailsRq> _list,Planner planner){
	    	List<PlanDetails> list = null;
	    	
	    	for(PlanDetailsRq dto : _list) {
	    		
	    		PlanDetails entity = PlanDetailsService.toEntity(dto);
	    		entity.setPlannerNo(planner);
	    		list.add(entity);
	    	}
	    	
	    	
	    	
			return list;
	    	
	    }
	    
	    public static List<PlanDetailsRq> toDtoList(List<PlanDetails> _list){
	    	
	    	List<PlanDetailsRq> list = null; 
	    	
	    	for(PlanDetails entity : _list) {
	    		PlanDetailsRq dto = PlanDetailsService.toDto(entity);
	    		list.add(dto);
	    	}
	    	
	    	return list;
	    }
	
	
}
