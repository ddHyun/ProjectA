package org.tourGo.service.destination;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.entity.QDestinationDetail;
import org.tourGo.models.destination.like.DestinationUidEntity;
import org.tourGo.models.plan.entity.Planner;
import org.tourGo.models.plan.entity.like.PlanUidEntity;

import com.querydsl.core.BooleanBuilder;

@Service
public class DestinationDetailService {

	@Autowired
	private DestinationDetailRepository destinationDetailRepository;
	
	@Autowired
	private DestinationUidEntityRepository uidEntityRepository;
	
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR/2egRn2vglLUfbXCP+2pblHvBggwbP1wMwnl/RvFZfHqob4GRBHbQDNn6IZP/Fg==";

	public List<DestinationDetail> detail_List(Long destinationNo) {

		return null;
	}

	// 특정 상세정보 불러오기
	public DestinationDetail dsDetailView(Long destinationNo) {
		Optional<DestinationDetail> test = destinationDetailRepository.findById(destinationNo);
		DestinationDetail ex = test.orElse(null);

		return ex;
	}

	// 조회수 증가
	public void updateTourHits(Long destinationNo) {
		destinationDetailRepository.updateTourHits(destinationNo);
	}
	/*
	 * public List<Planner> getPlannerLiked(Long userNo){
		//Optional<PlanUidEntity> wrapEntity = uidRepo.findByUserNo(userNo);
		//List<PlanUidEntity> uidEntity = (List<PlanUidEntity>) wrapEntity.orElse(null);
		List<PlanUidEntity> uidEntity = uidRepo.findByUserNo(userNo);
		System.out.println("유아이디");
		System.out.println(userNo);
		System.out.println(uidEntity);
		List<Planner> list = new ArrayList<>();
		for(PlanUidEntity _uid : uidEntity) {
			String uid = _uid.getUid();
			Long plannerNo = Long.parseLong(uid.split("_")[0]);
			System.out.println(plannerNo);
			Optional<Planner> _plannerEntity = plannerRepo.findById(plannerNo);
			Planner plannerEntity = _plannerEntity.orElse(null);
			list.add(plannerEntity);
			
		}
		
		return list;
	}
	 * 
	 * **/
	//사용자가 좋아요한 관광지 추출하는 메서드
	public List<DestinationDetail> getDetailListByUserNo(Long userNo){
		
		List<DestinationUidEntity> uidList = uidEntityRepository.findByUserNo(userNo);
		
		List<DestinationDetail> list = new ArrayList<>();
		
		for(DestinationUidEntity _uid : uidList) {
			String uid = _uid.getUid();
			Long destinationNo = Long.parseLong(uid.split("_")[0]);
			System.out.println(destinationNo);
			Optional<DestinationDetail> _detailEntity = destinationDetailRepository.findById(destinationNo);
			DestinationDetail detailsEntity = _detailEntity.orElse(null);
			list.add(detailsEntity);
		}
		
		System.out.println(list);
		return list;
	}
	

}
