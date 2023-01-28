package org.tourGo.service.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.plan.entity.Planner;

public interface PlannerEntityRepository{
//JpaRepository<Planner, Long>,QuerydslPredicateExecutor
	//조회수 증가  
//	@Modifying
//	@Transactional
//	@Query("update Planner p set p.hit=(p.hit+1) where p.plannerNo=:plannerNo")
//	int updatePlannerRead(@Param("plannerNo")Long plannerNo);
	
	//좋아요 업데이트
//	@Modifying
//	@Transactional
//	@Query("update Planner p set p.totalLikes=:totalLikes where p.plannerNo=:plannerNo")
//	int updateTotalLikes(int totalLikes, long plannerNo);
}
