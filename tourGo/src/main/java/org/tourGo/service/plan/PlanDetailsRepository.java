package org.tourGo.service.plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;

public interface PlanDetailsRepository extends JpaRepository<PlanDetails, Long>{

	List<PlanDetails> findAll();
	List<PlanDetails> findAllByPlanner(Planner planner,Sort sort);
	
}
