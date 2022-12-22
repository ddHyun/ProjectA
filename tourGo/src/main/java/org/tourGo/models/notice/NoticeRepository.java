package org.tourGo.models.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.entity.notice.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, QuerydslPredicateExecutor {
	
	//조회수 증가
	@Modifying
	@Transactional
	@Query("update Notice n set n.viewCount=(n.viewCount+1) where n.noticeNo=:noticeNo")
	int updateViewCount(@Param("noticeNo") Long noticeNo);
	
}
