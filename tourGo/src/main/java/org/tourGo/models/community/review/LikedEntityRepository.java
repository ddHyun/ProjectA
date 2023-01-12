package org.tourGo.models.community.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.tourGo.models.entity.community.review.LikedEntity;

public interface LikedEntityRepository extends JpaRepository<LikedEntity, String>, QuerydslPredicateExecutor {

	@Query("select l from LikedEntity l where l.uid like concat(:reviewNo, '\\_', '%')")
	List<LikedEntity> findUid(@Param("reviewNo") long reviewNo);
}
