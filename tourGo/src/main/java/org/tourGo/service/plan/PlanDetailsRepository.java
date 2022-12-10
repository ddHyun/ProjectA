package org.tourGo.service.plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.models.plan.entity.PlannerEntity;

public interface PlanDetailsRepository extends JpaRepository<PlanDetailsEntity, Long>{

	List<PlanDetailsEntity> findAll();
	List<PlanDetailsEntity> findAllByPlanner(Long plannerNo,Sort sort);
}
