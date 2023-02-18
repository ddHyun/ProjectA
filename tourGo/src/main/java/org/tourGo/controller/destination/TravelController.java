package org.tourGo.controller.destination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.service.destination.DestinationMainService;

@Controller
public class TravelController {

	@Autowired
	private DestinationMainService destinationMainService;

	// 비동기적 page 목록 뿌려주기
	@GetMapping("/api/travel")
	public String js(String keyword, Model model,
			@PathVariable(required = false, value = "page") String currPage,
			@PageableDefault(page = 0, size = 10, sort = "destinationNo", direction = Direction.DESC) Pageable pageable) {
		System.out.println("테스트 키워드");
		System.out.println(keyword);

//		List<DestinationDetail> list = destinationMainService.dest_mainList(keyword);
		Page<DestinationDetail> page = destinationMainService.dest_mainList(keyword, pageable);

		int nowPage = page.getPageable().getPageNumber() + 1;
		// -1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 9, page.getTotalPages());

		model.addAttribute("keyword", keyword);
		// pagination
		model.addAttribute("page", destinationMainService.dest_mainList(keyword, pageable));
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		
		System.out.println("====================keyword=======================");
		System.out.println(keyword);

		return "travel_destination/travel_destination_main::#allBox";
	}

}
