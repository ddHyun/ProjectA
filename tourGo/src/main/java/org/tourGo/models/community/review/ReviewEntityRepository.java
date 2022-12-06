package org.tourGo.models.community.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.entity.community.review.ReviewEntity;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Integer>{

	//검색 : findBy컬럼명(컬럼해당변수)
	//글번호로 내용조회
	ReviewEntity findByReviewNo(Long reviewNo);

	//조회수 증가
	@Modifying
	@Transactional
	@Query("update ReviewEntity r set r.reviewRead=(r.reviewRead+1) where r.reviewNo=:reviewNo")
	int updateReviewRead(@Param("reviewNo") Long reviewNo);

	//게시글 삭제
	int deleteByReviewNo(Long reviewNo);
}
