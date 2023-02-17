package org.tourGo.service.destination;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.entity.QDestinationDetail;

import com.querydsl.core.BooleanBuilder;

@Service
public class DestinationDetailService {

	@Autowired
	private DestinationDetailRepository destinationDetailRepository;

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

}
