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
	private PlanDetailsRepository repository;
		
	@Autowired
	private PlannerService plannerService;
		
	public PlanDetails userDetails(Planner planner){
		
		//QPlanDetails qPlan = QPlanDetails.planDetails;
		
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc("day"));
		orders.add(Order.desc("sdate"));
	
		List<PlanDetails> list = repository.findAllByPlanner(planner,Sort.by(orders));
		PlanDetails details = list.get(0);
		
		return details;
	}
	
	public boolean checkPlanner(User user,Planner planner) {

		if(planner.getUser() != user) {
			return false;
		}
			
		return true;
	}
	
	
}
