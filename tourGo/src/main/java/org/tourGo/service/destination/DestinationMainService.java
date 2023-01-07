package org.tourGo.service.destination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.entity.QDestinationDetail;

import com.querydsl.core.BooleanBuilder;

@Service
public class DestinationMainService {

	@Autowired
	private DestinationMainRepository destinationMainRepository;
	
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR/2egRn2vglLUfbXCP+2pblHvBggwbP1wMwnl/RvFZfHqob4GRBHbQDNn6IZP/Fg==";
	
	
	// DB에서 값 가져 오기
	public List<DestinationDetail> dest_mainList(String destination) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QDestinationDetail destinationdetail = QDestinationDetail.destinationDetail;
		
		if(!destination.equals("전체")) {
			builder.and(destinationdetail.tourDestination.eq(destination));
		}
		
		List<DestinationDetail> list = (List<DestinationDetail>) destinationMainRepository.findAll(builder);
		
		
		return list;
	}
	
	// 여행지 검색
	public List<DestinationDetail> dest_search(String title) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QDestinationDetail destinationDetail = QDestinationDetail.destinationDetail;
		
		builder.and(destinationDetail.tourTitle.contains(title));
		
		List<DestinationDetail> search = (List<DestinationDetail>) destinationMainRepository.findAll(builder, Sort.by(Sort.Direction.DESC,"destinationNo"));
		
		return search;
	}


	
}
