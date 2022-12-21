package org.tourGo.service.community.query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.common.AlertException;
import org.tourGo.controller.community.query.QueryRequest;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class QueryInfoService {

	@Autowired
	private QueryEntityRepository queryRepository;
	@Autowired
	private UserRepository userRepositoy;
	
	public QueryRequest process(Long queryNo) {
		QueryEntity queryEntity = queryRepository.findById(queryNo)
				.orElseThrow(() -> new AlertException("게시글이 존재하지 않습니다", "/community/query_main"));
		
//		User user = userRepositoy.findByUserId(queryEntity.getUser().getUserId())
//				.orElseThrow(() -> new AlertException("회원정보를 불러올 수 없습니다", "/community/query_main"));
		
		ModelMapper mapper = new ModelMapper();
		QueryRequest queryRequest = mapper.map(queryEntity, QueryRequest.class);
//		queryRequest.setUser(user);
		
		return queryRequest;
	}
}
