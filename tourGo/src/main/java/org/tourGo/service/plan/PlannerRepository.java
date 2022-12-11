package org.tourGo.service.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.models.plan.entity.PlannerEntity;

public interface PlannerRepository extends JpaRepository<PlannerEntity, Long>{

	
	//List<PlannerEntity> findAllByOrderByPlannerNoDESC();
	List<PlannerEntity> findAllByUser(String userId,Sort sort);

}
