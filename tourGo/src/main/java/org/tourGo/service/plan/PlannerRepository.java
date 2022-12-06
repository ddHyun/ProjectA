package org.tourGo.service.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.models.plan.entity.PlannerEntity;

public interface PlannerRepository extends JpaRepository<PlannerEntity, Long>{

}
