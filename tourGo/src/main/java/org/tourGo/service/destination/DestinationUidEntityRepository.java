package org.tourGo.service.destination;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.tourGo.models.destination.like.DestinationUid;
import org.tourGo.models.destination.like.DestinationUidEntity;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

public interface DestinationUidEntityRepository extends JpaRepository<DestinationUidEntity, DestinationUid>, QuerydslPredicateExecutor {

	
	//좋아요 총 개수
	@Query ("select count(u) from DestinationUidEntity u where u.field=:field and u.uid like concat(:destinationNo, '\\_', '%')")
	int countByUid(@Param("field") String field, @Param("destinationNo") long boardNo);

	//좋아요 목록
	@Query ("select u from DestinationUidEntity u where u.field=:field and u.uid like concat(:destinationNo, '\\_', '%', '\\_', :userNo)")
	Optional<DestinationUidEntity> findByNo(@Param("field") String field, @Param("destinationNo") long boardNo, @Param("userNo") long userNo);

	
		Optional<DestinationUidEntity> findByFieldAndUid(String field, String uid);
		//사용자가 좋아요한 목록
		@Query ("select u from DestinationUidEntity u where u.field='liked' and u.uid like concat('%', '\\_', :userNo)")
		List<DestinationUidEntity> findByUserNo(@Param("userNo") long userNo);	
}