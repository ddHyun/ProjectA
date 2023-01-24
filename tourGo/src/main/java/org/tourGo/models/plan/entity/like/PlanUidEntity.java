package org.tourGo.models.plan.entity.like;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="planuid")
@Getter @ToString
@IdClass(PlanUid.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class PlanUidEntity {
	
	@Id
	private String uid; //식별자
	@Id
	private String field; // 사용처
	
	@Builder
	public PlanUidEntity(String uid, String field) {
		this.uid = uid;
		this.field = field;
	}

}
