package org.tourGo.service.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.models.destination.like.DestinationUidEntity;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

@Service
public class DestinationLikeService {

	@Autowired
	private DestinationUidEntityRepository destinationLikeRepository;
	@Autowired
	private DestinationDetailRepository destinationDetailRepository;
	
	@Transactional
	public int process(String uid, String field) {
		
		DestinationUidEntity destinationUidEntity = destinationLikeRepository.findByFieldAndUid(field, uid).orElse(null);
		try {
			if(destinationUidEntity==null) {
				destinationUidEntity = destinationUidEntity.builder().uid(uid).field(field).build();
				destinationLikeRepository.save(destinationUidEntity);
			}else {
				destinationLikeRepository.delete(destinationUidEntity);
			}
		}catch (Exception e) {
			throw new AlertException("문제 발생 다시 시도","back");
		}
		
		long PlannerNo = Long.parseLong(uid.split("_")[0]);
		int totalLikes = destinationLikeRepository.countByUid(field, PlannerNo);
		System.out.println("카운트21231123");
		
		destinationDetailRepository.updateTotalLikes(totalLikes,PlannerNo);
		
		return totalLikes;
		
	}
}
