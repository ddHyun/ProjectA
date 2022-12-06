package org.tourGo.service.plan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.plan.entity.PlanDetailsEntity;

public interface PlanDetailsRepository extends JpaRepository<PlanDetailsEntity, Long>{

}
