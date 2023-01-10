package org.tourGo.models.community.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.community.review.LikedEntity;

public interface LikedEntityRepository extends JpaRepository<LikedEntity, String>, QuerydslPredicateExecutor {

	@Query("select count(l) from LikedEntity l where l.uid like concat(:reviewNo, '\\_', '%')")
	int countByUid(long reviewNo);
}
