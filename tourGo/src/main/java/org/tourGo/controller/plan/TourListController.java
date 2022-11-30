package org.tourGo.controller.plan;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.models.plan.tourList.TourList;

@RestController
public class TourListController {

	@GetMapping("/tourList")
	public List<TourList> tourList(){
		
		
		return null;
	}
}
