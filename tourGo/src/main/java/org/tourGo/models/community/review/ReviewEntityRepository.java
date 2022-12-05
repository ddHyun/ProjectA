package org.tourGo.models.community.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Integer>{

	//검색 : findBy컬럼명(컬럼해당변수)
	//글번호로 내용조회
	ReviewEntity findByReviewNo(int reviewNo);
	
	//전체목록 조회(정렬시 ByOrderBy)
	List<ReviewEntity> findAllByOrderByRegDtDesc();
	
	//후기등록
	@Modifying
	ReviewEntity save(ReviewEntity reviewEntity);

	//조회수 증가
	@Modifying
	@Transactional
	@Query("update ReviewEntity r set r.reviewRead=(r.reviewRead+1) where r.reviewNo=:reviewNo")
	int updateReviewRead(int reviewNo);

	//게시글 삭제
	int deleteByReviewNo(int reviewNo);
}
