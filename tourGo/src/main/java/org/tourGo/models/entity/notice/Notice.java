package org.tourGo.models.entity.notice;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Getter @Setter
public class Notice extends BaseEntity {
	
	@Id @GeneratedValue
	private Long noticeNo;
	
	@Column(nullable=false, length=100)
	private String noticeTitle;
	
	@Lob
	@Column(nullable=false)
	private String noticeContent;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate postStartDt; // 공지 게시 시작
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate postEndDt; // 공지 게시 끝
	
	@Column(nullable=false, columnDefinition="char(1) default 'N'", insertable=false)
	private char deleteYn;
	
	@Column(nullable=false, updatable=false)
	private String gid;								//file 그룹id
	
	@Column(columnDefinition = "int default '0'", insertable=false)
	private Integer viewCount;			//조회수
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")
	private User user;
}
