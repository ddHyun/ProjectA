package org.tourGo.models.community.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.entity.community.review.ReviewEntity;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Long>, QuerydslPredicateExecutor{

	//조회수 증가
	@Modifying
	@Transactional
	@Query("update ReviewEntity r set r.reviewRead=(r.reviewRead+1) where r.reviewNo=:reviewNo")
	int updateReviewRead(@Param("reviewNo") Long reviewNo);
	
	//좋아요 업데이트
	@Modifying
	@Transactional
	@Query("update ReviewEntity r set r.totalLikes=:totalLikes where r.reviewNo=:reviewNo")
	int updateTotalLikes(int totalLikes, long reviewNo);

}
