package org.tourGo.models.entity.community.query;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

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
	
	@Builder
	public QueryReplyEntity(Long queryNo, String queryReplyContent, QueryEntity query) {
		this.queryReplyNo = queryNo;
		this.queryReplyContent = queryReplyContent;
		this.query = query;
	}
}
