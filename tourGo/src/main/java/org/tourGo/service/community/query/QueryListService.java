package org.tourGo.service.community.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.entity.community.query.QueryEntity;

@Service
public class QueryListService {

	@Autowired
	private QueryEntityRepository queryRepository;
	
	public Page<QueryEntity> gets(){
		return gets(1);
	}
	
	public Page<QueryEntity> gets(int page) {
		return gets(page, 20);
	}
	
	public Page<QueryEntity> gets(int page, int limit) {
		limit = limit <= 0 ? 20 : limit;
		
		Pageable pageable = PageRequest.of(page-1, limit, Sort.by(Order.desc("regDt")));
		Page<QueryEntity> lists = queryRepository.findAll(pageable);
		
		return lists;
	}
}
