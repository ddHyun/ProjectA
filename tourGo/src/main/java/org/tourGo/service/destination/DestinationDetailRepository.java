package org.tourGo.service.destination;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.like.DestinationUidEntity;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

public interface DestinationDetailRepository extends JpaRepository<DestinationDetail, Long>, QuerydslPredicateExecutor{
	
	// 조회수 증가
	@Modifying
	@Transactional
	@Query("update DestinationDetail d set d.tourHits=( d.tourHits + 1 ) where d.destinationNo=:destinationNo")
	int updateTourHits(@Param("destinationNo") Long destinationNo);
	
	//좋아요 업데이트
	@Modifying
	@Transactional
	@Query("update DestinationDetail d set d.tourHeart=:tourHeart where d.destinationNo=:destinationNo")
	int updateTotalLikes(int tourHeart, long destinationNo);

}
