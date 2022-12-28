package org.tourGo.service.admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.controller.admin.AdminSearchRequest;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.entity.community.query.QQueryEntity;
import org.tourGo.models.entity.community.query.QueryEntity;

import com.querydsl.core.BooleanBuilder;

@Service
public class AdminQueryService {
	
	@Autowired
	private QueryEntityRepository queryRepository;
	
	public Page<QueryEntity> queryList(Pageable pageable, AdminSearchRequest request) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QQueryEntity query = QQueryEntity.queryEntity;
		
		String searchType = request.getSearchType();
		String searchKeyword = request.getSearchKeyword();
		
		if(searchType != null) {
			// 1. 제목 검색
			if("title".equals(searchType)) {
				booleanBuilder.and(query.queryTitle.contains(searchKeyword));
			} 
			// 2. 내용 검색
			else if("content".equals(searchType)){
				booleanBuilder.and(query.queryContent.contains(searchKeyword));
			}
			// 3. 제목+내용 검색
			else if("title_content".equals(searchType)) {
				booleanBuilder.or(query.queryTitle.contains(searchKeyword))
										.or(query.queryContent.contains(searchKeyword));
			}
			// 4. 작성자 검색
			else if("userId".equals(searchType)) {
				booleanBuilder.and(query.user.userId.contains(searchKeyword));
			}
		}
		
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("regDt")));
		
		return queryRepository.findAll(booleanBuilder, pageable);
	}
	
	public Optional<QueryEntity> findById(Long queryNo) {
		return queryRepository.findById(queryNo);
	}
	
	public List<QueryEntity> findTop3ByOrderByRegDtDesc() {
		return queryRepository.findTop3ByOrderByRegDtDesc();
	}
	
	public void updateQueryRead(Long queryNo) {
		queryRepository.updateQueryRead(queryNo);
	}
	
	@Transactional
	public void isSolvedSuccess(Long queryNo) {
		QueryEntity query = queryRepository.findById(queryNo).orElseThrow();
		
		query = QueryEntity.builder()
					.queryNo(query.getQueryNo())
					.queryTitle(query.getQueryTitle())
					.queryContent(query.getQueryContent())
					.user(query.getUser())
					.queryRead(query.getQueryRead())
					.isSolved(true)
					.build();
		
		queryRepository.save(query);
	}
}
