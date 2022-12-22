package org.tourGo.models.entity.destination;

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
public class Destination_Main {

	@Id @GeneratedValue
	private Long DestinationNo;// db테이블 증감번호

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userNo")
	private User user;// 관계매핑
	
	@Column(nullable = true)
	private Long tour_idx;	// 관광지 idx
	private String title; 	// 관광지 이름
	private String image1; 	// 관광지 이미지1
	private String image2; 	// 관광지 이미지2
	private int hits; 		// 관광지 조회수
	private int bookmark; 	// 관광지 좋아요

}
