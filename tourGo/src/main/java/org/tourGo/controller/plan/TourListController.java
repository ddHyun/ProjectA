package org.tourGo.controller.plan;

import java.io.UnsupportedEncodingException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.tourList.TourList;
import org.tourGo.models.plan.tourList.TourListDto;
import org.tourGo.service.plan.TestService;
import org.tourGo.service.plan.TourService;


@RestController
public class TourListController {

	@Autowired
	TourService service;
	@Autowired
	TestService testService;
/**
	@GetMapping("/tourList")
	public List<TourList> tourList(String keyword){
		List<TourList> items = service.process(keyword);
		
		
		return items;
	}*/
	
	
	@GetMapping("/tourList")
	public JsonResult<?> fetch(String keyword) throws Exception{
			List<TourListDto> list = testService.test(keyword);
			
		    System.out.println(keyword);
		    
			return new JsonResult<>(true, "성공", list);
	}
}
