package org.tourGo.models.destination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;
import org.tourGo.models.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDetail {
	
	@Id 
	@GeneratedValue
	private Long destinationNo;// db테이블 증감번호

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userNo")
	private User user;// 관계매핑

	@Column(nullable = true)
	private String tourTitle;			// 여행지 이름
	private String tourDestination; 	// 지역
	private String tourImg;				// 여행지 이미지
	private String tourDetaiIinfo;		// 여행지 상세정보
	private double mapX;				// 여행지 x좌표
	private double mapY;				// 여행지 y좌표
	private String tourAddr;			// 여행지 주소
	private String tourHomepage;		// 여행지 인터넷 사이트
	private String tourOperatingHour;	// 여행지 영업시간
	private String tourHoliday;			// 관광지 휴무일
	private String tourCharges;			// 관광지 이용요금
	private String tourNumber;			// 관광지 문의 연락처
	private Integer tourHits;			// 관광지 조회수
	private Integer tourHeart;			// 관광지 좋아요
}
