package org.tourGo.models.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>{

	//추가
	@Modifying //insert, update, delete문에 사용
	FileInfo save(@Param("fileInfo") FileInfo fileInfo);
	
	//조회(id)
	@Query("select f from FileInfo f where f.id=:id and f.success=1")
	FileInfo findById(@Param("id") long id);
	
	//조회(gid)
	@Query("select f from FileInfo f where f.gid=:gid and f.success=1")
	List<FileInfo> findByGid(@Param("gid") String gid);
	
	//삭제(id)
	@Modifying
	@Transactional //update, delete문에 사용
	int deleteById(long id);
	
	//삭제(gid)
	@Modifying
	@Transactional
	int deleteByGid(String gid);
	
	//작업완료 치리
	@Modifying
	@Transactional
	@Query("update FileInfo f set f.success=1 where f.gid=:gid")
	int updateSuccess(@Param("gid") String gid);
}
