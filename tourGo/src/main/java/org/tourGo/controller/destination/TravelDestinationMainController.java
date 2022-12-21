package org.tourGo.controller.destination;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelDestinationMainController {
	
	@GetMapping("/travel_destination_main")
	public String travel_dstination_main() {
		return "travel_destination/travel_destination_main";
	}
	
	@GetMapping("/destination_detail")
	public String dstination_detail() {
		return "travel_destination/destination_detail";
	}

}
