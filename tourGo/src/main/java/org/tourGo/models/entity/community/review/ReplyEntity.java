package org.tourGo.models.entity.community.review;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="reply")
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class ReplyEntity extends BaseEntity{

	@Id @GeneratedValue
	private Long replyNo;				//식별자
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reviewNo")
	private ReviewEntity review;	//게시글 번호
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")
	private User user;					//작성자
	
	@Lob
	@Column(nullable=false)
	private String replyContent;	//댓글내용
	
	private int depth;					
	private Long idParent;				//모댓글 글번호
	private String listOrder;				//1차 정렬
	@Column(nullable=false, columnDefinition="char(1) default 'N'", insertable=false)
	private String deleteYn; 					// 삭제 여부

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	
	//연관관계 편의메서드(한번에 양방향 관계를 설정하는 메서드)
	public void setReview(ReviewEntity review) {
		if(this.review != null) {	//기존 관계 제거하기
			this.review.getReplies().remove(this);
		}
		this.review = review;
		review.getReplies().add(this);
	}
	
	@Builder
	public ReplyEntity(Long replyNo, User user, String replyContent, 
								ReviewEntity review, int depth, Long idParent, 
								String listOrder, String deleteYn) {
		this.replyNo = replyNo;
		this.user = user;
		setReview(review);
		this.review = this.getReview();
		this.replyContent = replyContent;
		this.depth = depth;
		this.idParent = idParent;
		this.listOrder = listOrder;
		this.deleteYn = deleteYn;
	}

	@Override
	public String toString() {
		return "ReplyEntity [replyNo=" + replyNo + ", user=" + getUser() + ", review=" + getReview() + ", replyContent=" + replyContent + ", depth=" + depth + ", idParent="
				+ idParent + ", listOrder=" + listOrder + ", deleteYn=" + deleteYn + "]";
	}
	
}
