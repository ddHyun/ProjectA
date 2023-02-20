package org.tourGo.models.plan.entity.like;

import java.io.Serializable;

import org.tourGo.models.entity.community.review.MyUid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PlanUid implements Serializable {

	private String uid;
	private String field;
}
