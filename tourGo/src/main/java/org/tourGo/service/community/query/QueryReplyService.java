package org.tourGo.service.community.query;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.controller.community.query.QueryReplyRequest;
import org.tourGo.models.community.query.QueryReplyEntityRepository;
import org.tourGo.models.entity.community.query.QueryEntity;
import org.tourGo.models.entity.community.query.QueryReplyEntity;
import org.tourGo.service.admin.AdminQueryService;

@Service
public class QueryReplyService {
	
	@Autowired
	private QueryReplyEntityRepository replyRepository;
	
	@Autowired
	private AdminQueryService queryService;
	
	public Optional<QueryReplyEntity> findByQueryNo(Long queryNo) {
		QueryEntity query = queryService.findById(queryNo).orElseThrow();
		
		return replyRepository.findByQuery(query);
	}
	
	// 문의 사항 답변 등록/수정
	@Transactional
	public void registerQueryReply(QueryReplyRequest queryReplyRequest) {
		QueryEntity query = queryService.findById(queryReplyRequest.getQueryNo()).orElseThrow();
		
		QueryReplyEntity queryReplyEntity = QueryReplyEntity.builder()
																.queryNo(queryReplyRequest.getQueryReplyNo())
																.queryReplyContent(queryReplyRequest.getQueryReplyContent())
																.query(query)
																.user(queryReplyRequest.getUser())
																.build();
		
		replyRepository.save(queryReplyEntity);
	}
}
