package org.tourGo.service.community.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.common.AlertException;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.entity.community.query.QueryEntity;

@Service
public class QueryDeleteService {

	@Autowired
	private QueryEntityRepository repository;
	
	public void process(Long queryNo) {
		QueryEntity queryEntity = repository.findById(queryNo)
				.orElseThrow(() -> new AlertException("등록된 게시글이 아닙니다", "back"));
		
		try {
		repository.delete(queryEntity);
		}catch(RuntimeException e) {
			throw new AlertException("처리 도중 문제가 발생했습니다. 관리자에게 문의 바랍니다", "/community/query_main");
		}
	}
}
