package org.tourGo.controller.destination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.service.destination.DestinationDetailService;

@RestController
public class TravelController {
	
	@Autowired
	private DestinationDetailService destinationService;
	/**
	@GetMapping("/travel")
	public List<DestinationDetail> ex(@RequestParam String area , Model model) {
		
		List<DestinationDetail> list = destinationService.ex(area);
		
		
		
		return list;
	}
	*/
}
