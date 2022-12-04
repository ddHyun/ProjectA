package org.tourGo.models.entity.plan;

import java.time.LocalTime;
import javax.persistence.*;

<<<<<<< HEAD
=======
import org.tourGo.common.BaseEntity;

>>>>>>> 462b1199ec6ec4a5f8ae882a49ee5587cc0ee740
import lombok.*;

@Entity
@Getter @Setter
public class PlanDetail extends org.tourGo.common.BaseEntity {
	@Id @GeneratedValue
	private Long DetailNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")
	private Plan plan;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tourInfoId")
	private TourInfo tourInfo;
	
	private LocalTime sTime;
	private LocalTime eTime;
	
}
