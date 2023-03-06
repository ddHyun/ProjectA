package org.tourGo.controller.user;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.file.FileInfo;
import org.tourGo.service.user.MypageService;
import org.tourGo.service.user.UserService;
import org.tourGo.services.file.FileRUDService;

@Controller
public class MypageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileRUDService fileService;
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo-마이페이지";
	}
	@GetMapping("/user/mypage")
	public String mypage(Model model
									, @AuthenticationPrincipal PrincipalDetail principal) {
		model.addAttribute("addCss", new String[] {"user/mypage"});
		model.addAttribute("addScript", new String[] {"user/mypage", "fileUpload"});
		
		String userId = principal.getUsername();
		
		// 사용자 정보
		User user = userService.findByUserId(userId).orElseThrow();
		
		//그룹아이디 설정
		String gid = "";
		gid = user.getGid() == null ? ""+System.currentTimeMillis() : user.getGid();
		
		FileInfo file = fileService.findTopByGidOrderByRegDtDesc(gid);
		
		if(file != null) {
			long fileId = file.getId();
			model.addAttribute("profile", "/uploads/" + (fileId % 10) + "/" + fileId);
		} else {
			model.addAttribute("profile", "/images/user/img_avatar_default.png");
		}
		
		model.addAttribute("gid", gid);
		model.addAttribute("user", user);
		
		return "user/mypage";
	}
	
	@GetMapping("/user/mypage/{id}")
	public String mypage(Model model
									,@PathVariable("id") String userId) {
		
		model.addAttribute("addCss", new String[] {"user/mypage"});
		model.addAttribute("addScript", new String[] {"user/mypage", "fileUpload"});
		
		// 사용자 정보
		User user = userService.findByUserId(userId).orElseThrow();
		
		// 프로필 출력
		FileInfo file = fileService.findTopByGidOrderByRegDtDesc(user.getGid());
		if(file != null) {
			long fileId = file.getId();
			model.addAttribute("profile", "/uploads/" + (fileId % 10) + "/" + fileId);
		} else {
			model.addAttribute("profile", "/images/user/img_avatar_default.png");
		}
		
		model.addAttribute("user", user);
		
		return "user/mypageView";
	}
			
}
