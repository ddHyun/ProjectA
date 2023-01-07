package org.tourGo.controller.destination;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.destination.DestinationDetailRequest;

import org.tourGo.models.destination.entity.DestinationDetail;

import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.destination.DestinationDetailService;
import org.tourGo.service.destination.DestinationMainService;

@Controller
public class TravelDestinationMainController {

	@Autowired
	private DestinationMainService destinationMainService;
	
	@GetMapping("/travel_destination_main")
	public String ex(String destination, Model model,@RequestParam(name="destSearchKeyword",required=false)String keyword) {
		
		
		String[] addScript = new String[] { "destination/info" };
		model.addAttribute("addScript", addScript);
		
		if(keyword != null) {
			List<DestinationDetail> search = destinationMainService.dest_search(keyword);
			model.addAttribute("search", search);
		}
		return "travel_destination/travel_destination_main";
	}
	
	@ResponseBody
	@GetMapping("/api/travel/{destination}")
	public JsonResult<?> ex02(@PathVariable(name="destination", required=false) String destination
								, Model model) {
		
		System.out.println("테스트 : " + destination);
		List<DestinationDetail> list = destinationMainService.dest_mainList(destination);
		if(list.isEmpty()) {
			return new JsonResult<>(false, "값이 없습니다.", null);
		}
		
		return new JsonResult<>(true, "성공", list);
	}		

	@GetMapping("/destination_detail")
	public String dstination_detail() {
		return "travel_destination/destination_detail";
	}

	
}
