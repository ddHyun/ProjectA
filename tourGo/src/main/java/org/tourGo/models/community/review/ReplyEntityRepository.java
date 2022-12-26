package org.tourGo.models.community.review;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.community.review.ReplyEntity;

public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>, QuerydslPredicateExecutor{

	@Transactional
	void deleteByListOrder(String listOrder);
}
