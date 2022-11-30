package org.tourGo.models.plan.tourList;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourListItems {

	
	private List<TourList> tourItems;
}
