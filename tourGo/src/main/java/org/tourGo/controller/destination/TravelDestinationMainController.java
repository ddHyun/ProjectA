package org.tourGo.controller.destination;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.AlertException;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.destination.DestinationDetailRequest;

import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.destination.like.DestinationUidRequest;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.destination.DestinationDetailService;
import org.tourGo.service.destination.DestinationMainService;
import org.tourGo.service.destination.DestinationReadHitService;

@Controller
public class TravelDestinationMainController {

	@Autowired
	private DestinationMainService destinationMainService;

	@Autowired
	private DestinationDetailService destinationDetailService;
	
	@Autowired
	private DestinationReadHitService destinationReadHitService;

	@GetMapping(path={"/travel_destination_main"})
	public String ex(@RequestParam(name="keyword", defaultValue = "전체") String keyword,
			@PageableDefault(page=0, size=12, sort="destinationNo", direction=Sort.Direction.DESC) Pageable pageable,
			Model model) {
		
		System.out.println("==============currPage==============");
//		if(keyword==null) {
//			keyword="전체";
//		}
			
		System.out.println(keyword);
		// css, js 추가
		model.addAttribute("addCss", new String[] { "main/footer", "main/header", "destination/destination_main" });	
		String[] addScript = new String[] { "destination/info" };
		model.addAttribute("addScript", addScript);

		Page<DestinationDetail> page = destinationMainService.dest_mainList(keyword, pageable);
		
		int nowPage = page.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, page.getTotalPages());
		
		// 목록 바인딩 처리
        model.addAttribute("keyword", keyword);
		model.addAttribute("page", destinationMainService.dest_mainList(keyword, pageable));	
		model.addAttribute(
				"nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		
		return "travel_destination/travel_destination_main";
	}


	// 상세페이지 연결
	@GetMapping("/destination_detail/{destinationNo}")
	@Transactional
	public String dsDetail(@PathVariable long destinationNo, Model model,@AuthenticationPrincipal PrincipalDetail principal) {

		// css, js 추가
		model.addAttribute("addCss", new String[] { "/main/footer", "/main/header", "/destination/destination_main" });

		String[] addScript = new String[] { "destination/info" };
		model.addAttribute("addScript", addScript);

		System.out.println("______________________test------");
		System.out.println(destinationNo);
		DestinationDetail test = null;
		try {
			test = destinationDetailService.dsDetailView(destinationNo);
		} catch (Exception e) {
			throw new AlertException("에러");
		}
		Long userNo = null;
		
		if (principal != null) {
			model.addAttribute("user", principal.getUsername());
			userNo = principal.getUser().getUserNo();
		}
		

		String readUid = DestinationUidRequest.getUid(destinationNo, userNo);
		destinationReadHitService.process(readUid, "readHit", "destination");
		
		System.out.println("테스트입니다"+readUid);
		
		model.addAttribute("detail", test);

		return "travel_destination/destination_detail";
	}
	

}
