package org.tourGo.models.file;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import lombok.*;

@Entity 
@Table
@Getter @Setter @ToString
public class FileInfo extends BaseEntity{
	
	@Id @GeneratedValue
	private long id;		//파일 증감번호
	
	@Column(length=45, nullable=false)
	private String gid;		//그룹ID(그룹으로 묶어서 관리)	
	
	@Column(length=100, nullable=false)
	private String fileName;//원본파일명
	
	@Column(length=60, nullable=false)
	private String fileType;
	
	@Column(columnDefinition="TINYINT(1) default '0'")
	private int success;//파일등록여부 0:미완료/ 1:완료
	
}
