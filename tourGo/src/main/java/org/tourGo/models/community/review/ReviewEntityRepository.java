package org.tourGo.models.community.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Integer>{

	//검색 : findBy컬럼명(컬럼해당변수)
	//글번호로 내용조회
	ReviewEntity findByReviewNo(int reviewNo);
	
	//전체목록 조회
	List<ReviewEntity> findAll();
	
	//후기등록
	ReviewEntity save(ReviewEntity reviewEntity);
	
	//검색어로 결과 조회 예
//	@Query("select r from ReviewEntity r where r.reviewContents like %:reviewContents%")
//	List<ReviewEntity> findByReviewContents(@Param("reviewContents") String reviewContents);
}
