package org.tourGo.service.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.like.DestinationUidEntity;

@Service
public class DestinationReadHitService {

	
	@Autowired
	private DestinationUidEntityRepository destinationUidEntityRepository;
	@Autowired
	private DestinationDetailRepository destinationDetailRepository;
	
	public void process(String uid, String field, String boardName) {

		DestinationUidEntity destinationUidEntity = destinationUidEntityRepository.findByFieldAndUid(field, uid).orElse(null);
			
		if(destinationUidEntity == null) {
			destinationUidEntity = DestinationUidEntity.builder().uid(uid).field(field).build();
			destinationUidEntityRepository.save(destinationUidEntity);
			
			long boardNo = Long.parseLong(uid.split("_")[0]);
			
			switch(boardName) {
			case "destination":
				destinationDetailRepository.updateTourHits(boardNo);
				break;
				
			}
		}
	}
}
