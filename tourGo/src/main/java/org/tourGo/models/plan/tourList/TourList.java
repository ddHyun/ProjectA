package org.tourGo.models.plan.tourList;

import java.util.Date;

import org.tourGo.models.plan.Planner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourList {//관광지관련 dto
	
	private Long contentId;
    private String name;
    private String address;
    private String tel;
    private Integer sigungu;
    private Integer zipcode;
    private Float x;
    private Float y;
    private String image;
    

    //검색어
    private String searchKeyword;
	    
}
