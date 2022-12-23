package org.tourGo.models.destination;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestinationDetailRequest {	//dto(vo)같은것...?
	
	private Long destinationNo;
	@NotBlank()
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
