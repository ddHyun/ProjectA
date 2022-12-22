package org.tourGo.service.destination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.entity.DestinationMain;

@Service
public class DestinationMainService {

	@Autowired
	private DestinationMainRepository repository;
	
	// DB에 직접으로 접근하는 얘들 이라는 거지?
	
	public List<DestinationMain> process(Long userId) {
		
		List<DestinationMain> list = repository.findAllByUser(userId, Sort.by(Sort.Direction.DESC, "destinationNo"));
		
		return list;
	}
	
	
}
