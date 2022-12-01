package org.tourGo.common;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {
	//엔티티 공통 속성들 모음, 사용시 엔티티에서 상속받기
	
	@CreatedDate   	    //등록일
	@Column(updatable=false)
	private LocalDateTime regDt;
	
	@LastModifiedDate   //수정일
	private LocalDateTime modDt;
}
