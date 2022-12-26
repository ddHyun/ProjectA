package org.tourGo.service.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;

public interface PlannerRepository extends JpaRepository<Planner, Long>{

	
	//List<PlannerEntity> findAllByOrderByPlannerNoDESC();
		List<Planner> findAllByUser(User user,Sort sort);

}
