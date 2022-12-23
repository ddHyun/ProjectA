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
public class DestinationMainRequest {	//dto(vo)같은것...?
	
	private Long destinationNo;
	@NotBlank()
	private String tourTitle; 	// 관광지 이름
	private String tourImg; 	// 관광지 이미지
	private int tourHits; 		// 관광지 조회수
	private int tourHeart; 		// 관광지 좋아요
	
}
