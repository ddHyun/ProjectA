package org.tourGo.models.plan.details;

import java.time.LocalTime;
import java.util.Date;

public class PlanDetails {//여행 세부일정을 담당하는 dto
		private Long DetailsNo;
	    private Long plannerNo;
	    private LocalTime stime;
	    private LocalTime etime;
	    private String name;
	    private Float x;
	    private Float y;
	    private int day;
	    private String image;
}
