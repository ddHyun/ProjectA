package org.tourGo.models.community.review;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //클래스를 엔티티로 선언
@Table(name="review_t") //엔티티와 매핑할 테이블을 지정
@Getter
@Setter
@ToString
public class ReviewDto {

	@Id //=PK
	@GeneratedValue(strategy=GenerationType.AUTO) //=AI
	private int reviewNo;
	@Column(nullable=false, length=45)
	private String id;
	@Column(nullable=false, length=100)
	private String reviewTitle;
	@Column(nullable=false, length=20)
	private String region;
	@Column(nullable=false, length=20)
	private String period;
	@Column(nullable=false)
	private String reviewContent;
	@Column(length=100)
	private String image;
	private LocalDateTime reviewRegDt;
	@Column(columnDefinition="int default '0'")
	private int reviewRead;
}
