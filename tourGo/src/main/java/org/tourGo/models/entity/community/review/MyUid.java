package org.tourGo.models.entity.community.review;

import java.io.Serializable;

import lombok.*;

/* UidEntity 복합키 위한 식별자 클래스*/
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyUid implements Serializable{

	private String uid;
	private String field;
}
