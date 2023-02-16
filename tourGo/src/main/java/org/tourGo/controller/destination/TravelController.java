package org.tourGo.controller.destination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.service.destination.DestinationMainService;

@Controller
public class TravelController {
	
	@Autowired
	private DestinationMainService destinationMainService;
	
	//비동기적 
	@GetMapping("/api/travel")
	public String js(String keyword, Model model) {
		System.out.println("테스트 키워드");
		System.out.println(keyword);
		List<DestinationDetail> list = destinationMainService.dest_mainList(keyword);
		
		model.addAttribute("list",list);
		
	
		return "travel_destination/travel_destination_main::#allBox";
	}
	
	
}
