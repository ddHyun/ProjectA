package org.tourGo.service.destination;

import java.awt.print.Pageable;

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
	
	
	public Page<DestinationDetail> dest_detailList(Pageable pageable, String destination) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QDestinationDetail destinationdetail = QDestinationDetail.destinationDetail;
		
//		Page<DestinationDetail> list = destinationRepository.findAll(builder, pageable);
		
		return null;
	}

}
 