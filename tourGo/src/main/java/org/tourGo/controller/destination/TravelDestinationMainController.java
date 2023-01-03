package org.tourGo.controller.destination;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.destination.DestinationDetailRequest;

import org.tourGo.models.destination.entity.DestinationDetail;

import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.destination.DestinationDetailService;

@Controller
public class TravelDestinationMainController {

	@Autowired
	private DestinationDetailService destinationService;

	@GetMapping("/travel_destination_main")
	public String travel_dstination_main(Model model) {

		String[] addScript = new String[] { "destination/info" };
		model.addAttribute("addScript", addScript);
		
		

		DestinationDetailRequest main = new DestinationDetailRequest();
		model.addAttribute("main", main);

		return "travel_destination/travel_destination_main";
	}

	@GetMapping("/destination_detail")
	public String dstination_detail() {
		return "travel_destination/destination_detail";
	}

}
