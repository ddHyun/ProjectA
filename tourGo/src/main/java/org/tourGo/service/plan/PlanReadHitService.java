package org.tourGo.service.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

@Service
public class PlanReadHitService {

	@Autowired
	private PlanUidEntityRepository planUidEntityRepository;
	@Autowired
	private PlannerRepository plannerRepository;
	
	public void process(String uid, String field, String boardName) {
		
		PlanUidEntity planUidEntity = planUidEntityRepository.findByFieldAndUid(field, uid).orElse(null);
		
		if(planUidEntity == null) {
			planUidEntity = PlanUidEntity.builder().uid(uid).field(field).build();
			planUidEntityRepository.save(planUidEntity);
			
			long boardNo = Long.parseLong(uid.split("_")[0]);
			
			switch (boardName) {
			case "plan": 
				 plannerRepository.updatePlannerRead(boardNo);
				 break;
			

			}
		}
	}
}
