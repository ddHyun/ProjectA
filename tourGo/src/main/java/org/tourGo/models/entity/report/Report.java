package org.tourGo.models.entity.report;

/**
 * 문의 게시물, 사용자
 */

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Getter @Setter
public class Report extends BaseEntity {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String target;
	
	@Column(nullable=false)
	private Long targetId;
	
	private String title;
	
	private String content;
	
	private String processYn;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	private User user;
}
