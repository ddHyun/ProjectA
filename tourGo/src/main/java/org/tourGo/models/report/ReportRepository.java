package org.tourGo.models.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tourGo.models.entity.user.User;

@Repository
public interface ReportRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor {

}
