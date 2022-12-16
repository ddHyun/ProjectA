package org.tourGo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tourGo.common.JsonResult;
import org.tourGo.common.Pagination;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.admin.AdminService;


@Controller
public class AdminMainController {
	
	@Autowired
	private AdminService adminService;
	
	private String base_url = "/admin";
	
	@GetMapping("/index")
	public String main_index() {
		System.out.println("메인 페이지로 이동!!");
		return "index";
	}
	
	@GetMapping("/admin/index")
	public String admin_index(Model model,
											@AuthenticationPrincipal PrincipalDetail principal) {
		
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/demo/chart-area-demo", "admin/demo/chart-pie-demo"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min", "chart.js/Chart.min"});
		return  "admin/index";
	}
	
	@GetMapping("/admin/user/userManage")
	public String userManage(@PageableDefault Pageable pageable,
											SearchRequest searchRequest,
											Model model) {
		
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/adminManage"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		Page<User> list = adminService.userManage(pageable, searchRequest);
		Pagination<User> pagination = new Pagination<>(list, base_url + "/user/userManage");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/user/userManage";
	}
	
	@GetMapping("/admin/user/userManageView/{userId}")
	public String userManageView(@PathVariable("userId") String userId,
													Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/adminManage"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		User user = adminService.userManageView(userId).orElseThrow(() -> {
			throw new RuntimeException("해당 사용자가 존재하지 않습니다.");
		});
		
		model.addAttribute("user", user);
		
		return "admin/user/userManageView";
	}
	
	@PostMapping("/admin/user/userManageView/{userNo}")
	public String userManageViewPs(@PathVariable("userNo") Long userNo,
													Model model) {
		adminService.activeTypeChange(userNo);
		
		return "redirect:/admin/user/userManage";
	}
	
	@GetMapping("/admin/user/userActiveManage")
	public String userActiveManage(@PageableDefault Pageable pageable,
													SearchRequest searchRequest,
													Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/adminManage"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min" , "bootstrap/js/bootstrap.min"});
		
		Page<User> list = adminService.userActiveManage(pageable, searchRequest);
		Pagination<User> pagination = new Pagination<>(list, base_url + "/user/userActiveManage");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/user/userActiveManage";
	}
	
	@GetMapping("/admin/user/adminTypeManage")
	public String adminTypeManage(@PageableDefault Pageable pageable,
														SearchRequest searchRequest,
														Model model) {
		
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {"admin/adminManage"});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
		
		Page<User> list = adminService.adminTypeManage(pageable);
		Pagination<User> pagination = new Pagination<>(list, base_url + "/user/adminTypeManage");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		
		return "admin/user/adminTypeManage";
	}
	
	@ResponseBody
	@GetMapping("/admin/user/adminTypeChange/{id}")
	public JsonResult<?> adminTypeChange(@PathVariable("id") Long id) {
		adminService.adminTypeChange(id);
		
		return new JsonResult<>(true, "등급 변경이 완료되었습니다.", null);
	}
	
	@ResponseBody
	@GetMapping("/admin/user/activeTypeChange/{id}")
	public JsonResult<?> activeTypeChange(@PathVariable("id") Long id) {
		adminService.activeTypeChange(id);
		
		return new JsonResult<>(true, "활동 여부 변환이 완료되었습니다.", null);
	}
}
