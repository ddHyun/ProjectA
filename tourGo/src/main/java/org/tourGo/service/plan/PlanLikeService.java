package org.tourGo.service.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.common.AlertException;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

@Service
public class PlanLikeService {

	@Autowired
	private PlanUidEntityRepository planLikeRepository;
	@Autowired
	private PlannerRepository plannerRepository;
	
	@Transactional
	public int process(String uid, String field) {
		
		PlanUidEntity planUidEntity = planLikeRepository.findByFieldAndUid(field, uid).orElse(null);
		try {
			if(planUidEntity==null) {
				planUidEntity = planUidEntity.builder().uid(uid).field(field).build();
				planLikeRepository.save(planUidEntity);
			}else {
				planLikeRepository.delete(planUidEntity);
			}
		}catch (Exception e) {
			throw new AlertException("문제 발생 다시 시도","back");
		}
		
		long PlannerNo = Long.parseLong(uid.split("_")[0]);
		int totalLikes = planLikeRepository.countByUid(field, PlannerNo);
		
		plannerRepository.updateTotalLikes(totalLikes,PlannerNo);
		
		return totalLikes;
		
	}
}
