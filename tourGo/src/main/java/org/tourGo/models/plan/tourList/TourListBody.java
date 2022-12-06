package org.tourGo.models.plan.tourList;

import java.util.List;



import lombok.*;

@Getter @Setter @ToString
public class TourListBody {
	
	private TourListItems items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
