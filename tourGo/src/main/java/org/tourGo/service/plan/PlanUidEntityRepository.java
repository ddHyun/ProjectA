package org.tourGo.service.plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.tourGo.models.plan.entity.like.PlanUid;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

public interface PlanUidEntityRepository extends JpaRepository<PlanUidEntity, PlanUid>, QuerydslPredicateExecutor {

		//좋아요 총 개수
		@Query ("select count(u) from PlanUidEntity u where u.field=:field and u.uid like concat(:plannerNo, '\\_', '%')")
		int countByUid(@Param("field") String field, @Param("plannerNo") long boardNo);
	
		//좋아요 목록
		@Query ("select u from PlanUidEntity u where u.field=:field and u.uid like concat(:plannerNo, '\\_', '%', '\\_', :userNo)")
		Optional<PlanUidEntity> findByNo(@Param("field") String field, @Param("plannerNo") long boardNo, @Param("userNo") long userNo);

		Optional<PlanUidEntity> findByFieldAndUid(String field, String uid);
		
		@Query ("select u from PlanUidEntity u where u.field='liked' and u.uid like concat('%', '\\_', :userNo)")
		List<PlanUidEntity> findByUserNo(@Param("userNo") long userNo);
 
		
		
}