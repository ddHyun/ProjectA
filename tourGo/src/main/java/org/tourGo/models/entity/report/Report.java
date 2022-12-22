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
	
	@Column(name="reportNo")
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String target; // 대상(사용자, 게시물, 댓글 등)
	
	@Column(nullable=false)
	private Long targetId; // 대상 아이디
	
	private String title;
	
	@Lob
	private String content;
	
	private String processYn; // 처리 여부
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="userNo")
	private User user;
}
