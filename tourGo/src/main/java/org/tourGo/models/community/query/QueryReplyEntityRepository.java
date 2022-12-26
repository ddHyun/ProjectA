package org.tourGo.models.community.query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.community.query.QueryReplyEntity;

public interface QueryReplyEntityRepository extends JpaRepository<QueryReplyEntity, Long>, QuerydslPredicateExecutor {
	
	Optional<QueryReplyEntity> findByQuery(QueryEntity query);
	
}
