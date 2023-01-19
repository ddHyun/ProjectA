package org.tourGo.models.community.review;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.tourGo.models.entity.community.review.MyUid;
import org.tourGo.models.entity.community.review.UidEntity;

public interface UidEntityRepository extends JpaRepository<UidEntity, MyUid>, QuerydslPredicateExecutor {

	//좋아요 총 개수
	@Query("select count(u) from UidEntity u where u.field=:field and u.uid like concat(:reviewNo, '\\_', '%')")
	int countByUid(@Param("field") String field, @Param("reviewNo") long boardNo);
	
	//좋아요 목록
	@Query("select u from UidEntity u where u.field=:field and u.uid like concat(:reviewNo, '\\_', '%', '\\_', :userNo)")
	Optional<UidEntity> findByNo(@Param("field") String field, @Param("reviewNo") long boardNo, @Param("userNo") long userNo);
	
	Optional<UidEntity> findByFieldAndUid(String field, String uid);
}
