package org.tourGo.services.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tourGo.models.file.FileInfo;
import org.tourGo.models.file.FileInfoRepository;

@Service
public class FileUploadService {
	
	@Value("${file.uploadPath}")
	private String uploadPath;
	
	@Autowired
	private FileInfoRepository repository;
	
	public List<FileInfo> process(MultipartFile[] files) {
		
		String gid = ""+System.currentTimeMillis();
		
		return process(files, gid);
	}
	
	public List<FileInfo> process(MultipartFile[] files, String gid){
		
		if(gid==null||gid.isBlank()) {
			gid = ""+System.currentTimeMillis();
		}
		
		List<FileInfo> fileLists = new ArrayList<>();
		
		//DB저장
		for(MultipartFile file : files) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setGid(gid);
			fileInfo.setFileName(file.getOriginalFilename());
			fileInfo.setFileType(file.getContentType());
			
			fileInfo = repository.save(fileInfo);
			
			//id로 업로드파일경로 설정
			long id = fileInfo.getId();
			String uploadDir = uploadPath+"/"+(id%10);
			File _uploadDir = new File(uploadDir);
			if(!_uploadDir.exists()) {//폴더 없으면 생성
				_uploadDir.mkdirs();
			}
			String uploadFilePath = uploadDir+"/"+id;
			
			//파일 업로드
			try {//업로드 성공한 file정보만 담기
				file.transferTo(new File(uploadFilePath));
				repository.updateSuccess(fileInfo.getGid());
				fileLists.add(fileInfo);
			} catch (Exception e) {//실패시 실패파일 정보 삭제
				e.printStackTrace();
				repository.deleteById(id);
				throw new RuntimeException("업로드 실패");
			}
		}			
		return fileLists;
	}
	
}
