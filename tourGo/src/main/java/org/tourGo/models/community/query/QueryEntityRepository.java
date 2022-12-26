package org.tourGo.models.community.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.entity.community.query.QueryEntity;

public interface QueryEntityRepository extends JpaRepository<QueryEntity, Long>, QuerydslPredicateExecutor{
	
	List<QueryEntity> findTop3ByOrderByRegDtDesc();
	
	//조회수 증가
	@Modifying
	@Transactional
	@Query("update QueryEntity q set q.queryRead=(q.queryRead+1) where q.queryNo=:queryNo")
	int updateQueryRead(@Param("queryNo") Long queryNo);
	
}
