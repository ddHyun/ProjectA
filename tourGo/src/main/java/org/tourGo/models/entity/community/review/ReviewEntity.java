package org.tourGo.models.entity.community.review;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="review")
@Getter @Setter @ToString
public class ReviewEntity extends BaseEntity {

	@Id @GeneratedValue
	private Long reviewNo;
	
	@ManyToOne(fetch=FetchType.LAZY)		
	@JoinColumn(name="userNo")				
	private User user;
	
	@Column(nullable=false, length=100)
	private String reviewTitle;
	@Column(nullable=false, length=20)
	private String region;
	@Column(nullable=false, length=20)
	private String period;
	@Lob
	@Column(nullable=false)
	private String reviewContent;
	@Column(nullable=false)
	private String gid;	
	@Column(columnDefinition = "int default '0'", insertable=false)
	private int reviewRead;
	
}
