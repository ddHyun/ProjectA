package org.tourGo.controller.file;

import java.util.List;
import org.tourGo.common.JsonResult;
import org.tourGo.models.file.FileInfo;
import org.tourGo.services.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	// ControllerAdvice
	// RestControllerAdvice
	@Autowired
	private FileUploadService service;
	
	@PostMapping("/file/upload")
	public JsonResult<List<FileInfo>> process(String gid, MultipartFile[] file) {
		List<FileInfo> fileInfos = service.process(file, gid);
		
		JsonResult<List<FileInfo>> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(fileInfos);
		
		return result;
	}
	
	
	@ExceptionHandler(Exception.class)
	public JsonResult<Object> errorHandle(Exception e) {
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		
		return result;
	}
}
