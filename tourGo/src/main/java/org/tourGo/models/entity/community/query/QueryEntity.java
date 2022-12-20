package org.tourGo.models.entity.community.query;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="query")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
	
	@Column(columnDefinition="boolean default false", updatable=false)
	private boolean isSolved; 	//답변완료 유무 확인
	
	@Builder
	public QueryEntity(String queryTitle, String queryContent, User user, int queryRead, boolean isSolved) {
		this.queryTitle = queryTitle;
		this.queryContent = queryContent;
		this.user = user;
		this.queryRead = queryRead;
		this.isSolved = isSolved;
	}
}
