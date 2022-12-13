package org.tourGo.service.plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.PlanDetails;

@Service
public class PlanDetailsService {

	@Autowired
	private PlanDetailsRepository repository;
		
	public List<PlanDetails> userDetails(Long plannerNo){
		
		
		
		
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc("day"));
		orders.add(Order.desc("sdate"));
	
		List<PlanDetails> list = repository.findAllByPlanner(plannerNo,Sort.by(orders));
		
		
		return list;
	}
	
	
}
