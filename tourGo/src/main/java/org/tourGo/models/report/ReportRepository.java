package org.tourGo.models.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tourGo.models.entity.report.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, QuerydslPredicateExecutor {

}
