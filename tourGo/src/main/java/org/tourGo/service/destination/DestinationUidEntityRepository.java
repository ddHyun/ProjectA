package org.tourGo.service.destination;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.tourGo.models.destination.like.DestinationUid;
import org.tourGo.models.destination.like.DestinationUidEntity;

public interface DestinationUidEntityRepository extends JpaRepository<DestinationUidEntity, DestinationUid>, QuerydslPredicateExecutor {

		Optional<DestinationUidEntity> findByFieldAndUid(String field, String uid);
}