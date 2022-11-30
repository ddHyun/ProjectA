package org.tourGo.models.plan.tourList;

import java.util.List;



import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourListResult {
	private TourListHeader header;
	private TourListBody body;
}
