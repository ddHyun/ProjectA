package org.tourGo.models.destination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.tourGo.models.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationMain {

	@Id @GeneratedValue
	private Long destinationMainNo;// db테이블 증감번호

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userNo")
	private User user;// 관계매핑
	
	@Column(nullable = true)
	private String tourTitle; 	// 관광지 이름
	private String tourImg; 	// 관광지 이미지
	private int tourHits; 		// 관광지 조회수
	private int tourHeart; 		// 관광지 좋아요

}
