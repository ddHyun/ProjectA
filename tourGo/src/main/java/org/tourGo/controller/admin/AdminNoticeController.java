package org.tourGo.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.config.auth.PrincipalDetail;
import org.tourGo.models.entity.notice.Notice;
import org.tourGo.models.file.FileInfo;
import org.tourGo.service.admin.AdminNoticeService;
import org.tourGo.services.file.FileRUDService;
import org.tourGo.services.file.FileUploadService;

@Controller
public class AdminNoticeController {
	
	@Autowired
	private AdminNoticeService noticeService;
	
	@Autowired
	private FileUploadService uploadService;
	
	@Autowired
	private FileRUDService fileService;
	@ModelAttribute("siteTitle")
	public String getSiteTitle() {
		
		return "TourGo";
	}
	private String base_url = "/admin";
	
	private void addCommons(Model model) {
		// 부트스트랩 관련 CSS 추가
		model.addAttribute("addCss", new String[] {"admin/sb-admin-2"});
		model.addAttribute("addBootstrapCss", new String[] {"fontawesome-free/css/all", "datatables/dataTables.bootstrap4"});
		
		// 부트스트랩 관련 JS 추가
		model.addAttribute("addScript", new String[] {});
		model.addAttribute("addBootstrapJs", new String[] {"jquery/jquery.min", "jquery-easing/jquery.easing.min", "bootstrap/js/bootstrap.bundle.min", "bootstrap/js/bootstrap.min"});
	}
	
	@GetMapping("/admin/board/noticeList")
	public String noticeList(Model model,
										@PageableDefault Pageable pageable,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										AdminSearchRequest searchRequest) {
		
		addCommons(model);
		Page<Notice> list = noticeService.noticeList(pageable, searchRequest);
		Pagination<Notice> pagination = new Pagination<>(list, base_url + "/board/noticeList");
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("searchRequest", searchRequest);
		
		return "admin/board/noticeList";
	}
	
	@GetMapping("/admin/board/noticeView/{noticeNo}")
	public String noticeView(Model model,
										@PathVariable("noticeNo") Long noticeNo,
										@RequestParam(name="searchType", required=false) String searchType,
										@RequestParam(name="searchKeyword", required=false) String searchKeyword,
										@RequestParam(name="page", required=false) String page,
										AdminNoticeRequest adminNoticeRequest) {
		
		
		addCommons(model);
		Notice notice = noticeService.findById(noticeNo).orElse(null);
		adminNoticeRequest = new AdminNoticeRequest(notice);
		
		List<FileInfo> fileList = fileService.getFileLists(notice.getGid());
		
		model.addAttribute("adminNoticeRequest", adminNoticeRequest);
		model.addAttribute("fileList", fileList);
		model.addAttribute("page", page);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "admin/board/noticeView";
	}
	
	@GetMapping("/admin/board/noticeRegist")
	public String noticeRegist(Model model,
										@AuthenticationPrincipal PrincipalDetail principal,
										@RequestParam(name="noticeNo", required=false) Long noticeNo,
										AdminNoticeRequest adminNoticeRequest,
										AdminSearchRequest searchRequest) {
		
		addCommons(model);
		model.addAttribute("addScript", new String[] {"admin/noticeRegist", "ckeditor/ckeditor", "fileupload"});
		
		if(noticeNo != null) {
			Notice notice = noticeService.findById(noticeNo).orElse(null);
			adminNoticeRequest = new AdminNoticeRequest(notice);
		} else {
			adminNoticeRequest = new AdminNoticeRequest();
		}
		
		//그룹아이디 설정
		String gid = adminNoticeRequest.getGid() == null ? ""+System.currentTimeMillis() : adminNoticeRequest.getGid();
		
		model.addAttribute("user", principal.getUsername());
		model.addAttribute("adminNoticeRequest", adminNoticeRequest);
		model.addAttribute("gid", gid);
		
		return "admin/board/noticeRegist";
	}
	
	@PostMapping("/admin/board/noticeRegist")
	public String noticeRegistPs(Model model,
												@AuthenticationPrincipal PrincipalDetail principal,
												Long noticeNo,
												@Valid AdminNoticeRequest adminNoticeRequest,
												Errors errors) {
		
		if(errors.hasErrors()) {
			addCommons(model);
			
			return "admin/board/noticeRegist";
		}
		
		String userId = principal.getUsername();
		
		try {
			adminNoticeRequest.setUserId(userId);
			noticeService.registerNotice(adminNoticeRequest);
			// 파일 업로드 완료 처리 
			uploadService.updateSuccess(adminNoticeRequest.getGid());
		} catch (Exception e) {
			errors.reject("notice_write_error", e.getMessage());
			addCommons(model);
			
			return "admin/board/noticeRegist";
		}
		
		return "redirect:/admin/board/noticeList";
	}
}
