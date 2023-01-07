package org.tourGo.service.destination;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.entity.QDestinationDetail;

import com.querydsl.core.BooleanBuilder;

@Service
public class DestinationDetailService {
	
	@Autowired
	private DestinationDetailRepository destinationRepository;
	
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR/2egRn2vglLUfbXCP+2pblHvBggwbP1wMwnl/RvFZfHqob4GRBHbQDNn6IZP/Fg==";
	
	
	// DB에서 값 가져 오기
	public List<DestinationDetail> dest_detailList(String destination) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QDestinationDetail destinationdetail = QDestinationDetail.destinationDetail;
		
		if(!destination.equals("전체")) {
			builder.and(destinationdetail.tourDestination.eq(destination));
		}
		
		List<DestinationDetail> list = (List<DestinationDetail>) destinationRepository.findAll(builder);
		
		
		return list;
	}

}
 