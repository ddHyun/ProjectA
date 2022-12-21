package org.tourGo.models.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.tourGo.models.entity.notice.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, QuerydslPredicateExecutor {

}
