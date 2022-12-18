package org.tourGo.models.entity.community.query;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="query")
@Getter @Setter
public class QueryEntity extends BaseEntity{

	@Id @GeneratedValue
	private Long queryNo;
	
	@Column(nullable=false, length=100)
	private String queryTitle;
	
	@Lob
	@Column(nullable=false)
	private String queryContent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")
	private User user;
	
	@Column(columnDefinition="int default '0'", insertable=false, updatable=false)
	private int queryRead;
}
