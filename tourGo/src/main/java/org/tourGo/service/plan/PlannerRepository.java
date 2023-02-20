package org.tourGo.service.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.entity.Planner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


public interface PlannerRepository extends JpaRepository<Planner, Long>, QuerydslPredicateExecutor{

	
	//List<PlannerEntity> findAllByOrderByPlannerNoDESC();
	List<Planner> findAllByUser(User user,Sort sort); 
		Page<Planner> findByTitleContaining(String searchKeyword, Pageable pageable);
		
	//조회수 증가
	@Modifying
	@Transactional
	@Query("update Planner p set p.PlannerRead=(p.PlannerRead+1) where p.plannerNo=:plannerNo")
	int updatePlannerRead(@Param("plannerNo") Long plannerNo);
	
	//좋아요 업데이트
	@Modifying
	@Transactional
	@Query("update Planner p set p.totalLikes=:totalLikes where p.plannerNo=:plannerNo")
	int updateTotalLikes(int totalLikes, long plannerNo);
	//좋아요 탑3
	List<Planner> findTop3ByOpen(Boolean open,Sort sort);
	

}