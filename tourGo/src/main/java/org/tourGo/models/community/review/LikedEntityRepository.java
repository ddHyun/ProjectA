package org.tourGo.models.community.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.community.review.LikedEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;

public interface LikedEntityRepository extends JpaRepository<LikedEntity, Long>, QuerydslPredicateExecutor {

	long countByReviewAndLiked(ReviewEntity review, boolean liked);
}
