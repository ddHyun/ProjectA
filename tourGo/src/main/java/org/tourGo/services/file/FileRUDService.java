package org.tourGo.services.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.file.FileInfo;
import org.tourGo.models.file.FileInfoRepository;

@Service
public class FileRUDService {

	@Autowired
	private FileInfoRepository repository;
	
	//id로 파일 조회하기
	public FileInfo getOneList(long id) {
		FileInfo list = repository.findById(id);
		if(list==null) {
			throw new RuntimeException("파일이 없습니다");
		}
		return list;
	}
	
	//gid로 파일 조회하기
	public List<FileInfo> getFileLists(String gid){
		List<FileInfo> fileLists = repository.findByGid(gid);
		if(fileLists.size()==0) {
			throw new RuntimeException("파일이 없습니다");
		}
		return fileLists;
	}
	
	//id로 파일 삭제하기
	public boolean removeOneList(long id) {
		int affectedRow = repository.deleteById(id);
		return affectedRow > 0;
	}
	
	//gid로 파일 삭제하기
	public boolean removeFileLists(String gid) {
		int affectedRows = repository.deleteByGid(gid);
		return affectedRows > 0;
	}
}
