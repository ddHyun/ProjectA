package org.tourGo.service.community.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.community.query.QueryEntityRepository;

@Service
public class QueryListService {

	@Autowired
	private QueryEntityRepository queryRepository;
	

}
