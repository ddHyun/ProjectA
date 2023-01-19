package org.tourGo.models.entity.community.review;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="uid")
@Getter @ToString
@IdClass(MyUid.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UidEntity {

	@Id
	private String uid; // 식별자 	
	@Id
	private String field;	//사용처

	@Builder
	public UidEntity(String uid, String field) {
		this.uid = uid;
		this.field = field;
	}
}
