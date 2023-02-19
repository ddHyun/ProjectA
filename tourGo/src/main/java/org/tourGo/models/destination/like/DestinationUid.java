package org.tourGo.models.destination.like;

import java.io.Serializable;

import org.tourGo.models.entity.community.review.MyUid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DestinationUid implements Serializable {

	private String uid;
	private String field;
}
