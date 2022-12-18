package org.tourGo.models.community.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.community.query.QueryEntity;

public interface QueryEntityRepository extends JpaRepository<QueryEntity, Long>, QuerydslPredicateExecutor{

}
