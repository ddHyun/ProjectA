package org.tourGo.service.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long>, QuerydslPredicateExecutor{

	
	//List<PlannerEntity> findAllByOrderByPlannerNoDESC();
	List<Planner> findAllByUser(User user,Sort sort); 
		Page<Planner> findByTitleContaining(String searchKeyword, Pageable pageable);

		

}