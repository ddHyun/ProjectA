package org.tourGo.models.entity.community.query;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="queryReply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QueryReplyEntity extends BaseEntity {
	
	@Id @GeneratedValue
	private Long queryReplyNo;
	
	@Lob
	@Column(nullable=false)
	private String queryReplyContent;
	
	@OneToOne
	@JoinColumn(name="queryNo")
	private QueryEntity query;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")
	private User user;
	
	@Builder
	public QueryReplyEntity(Long queryNo, String queryReplyContent, QueryEntity query, User user) {
		this.queryReplyNo = queryNo;
		this.queryReplyContent = queryReplyContent;
		this.query = query;
		this.user = user;
	}
}
