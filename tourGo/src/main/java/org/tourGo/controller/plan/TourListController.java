package org.tourGo.controller.plan;

import java.io.UnsupportedEncodingException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.AlertException;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.tourList.TourList;
import org.tourGo.models.plan.tourList.TourListDto;

import org.tourGo.service.plan.TourService;


@RestController
public class TourListController {

	@Autowired
	TourService tourService;
	

	
 /* @throws Exception */
	@GetMapping("/tourList")
	public JsonResult<?> fetch(String keyword){
			List<TourListDto> list = null;
			
				try {
					list = tourService.loadApi(keyword);
				} catch (Exception e) {
					e.printStackTrace();
				throw new AlertException("다시 시도해주세요.");
				}
			
		    
			return new JsonResult<>(true, "성공", list);
	}
}
