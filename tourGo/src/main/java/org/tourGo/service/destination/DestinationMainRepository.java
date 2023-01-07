package org.tourGo.service.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.destination.entity.DestinationDetail;

public interface DestinationMainRepository extends JpaRepository<DestinationDetail, Long>, QuerydslPredicateExecutor {

}
