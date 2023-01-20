package org.tourGo.controller.destination;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.service.destination.DestinationService;

@Controller
public class DestinationAreaController {
	
	@Autowired
	private DestinationService destinationService;
	
	private void addCommons(Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/destinationList"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
	}
	
	/** 
	 * 각 지역의 관광 데이터 불러오기 목록 페이지
	 * 
	 */
	@GetMapping("/admin/destination/destinationList")
	public String destinationList(Model model) {
		
		addCommons(model);
		
		return "admin/destination/destinationList";
	}
	
	/** 
	 * 각 지역의 관광 데이터 불러오기 목록 처리
	 * 
	 */
	@ResponseBody
	@GetMapping("/admin/destination/destinationCallBack/{areaCode}")
	public JsonResult<?> areaCodeCallBack(@AuthenticationPrincipal PrincipalDetail principal,
												@PathVariable(name="areaCode") int areaCode) throws IOException {
		
		String userId = principal.getUsername();
		
		destinationService.responseList(areaCode, userId);
		
		return new JsonResult<>(true, "정상적으로 데이터가 추가되었습니다.", null);
	}
	
}
