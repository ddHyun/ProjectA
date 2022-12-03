package org.tourGo.models.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;

@Getter @Setter
@Entity

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime regDt;
	@LastModifiedDate
	private LocalDateTime modDt;
}
