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
public class DestinationRequest {	//dto(vo)같은것...?
	
	private Long destinationNo;
	@NotBlank()
	private String name;	//관광지 이름
	private String address;	//관광지 주소
	private String image;	//관광지 이미지

}
