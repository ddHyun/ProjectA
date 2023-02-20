package org.tourGo.models.destination.like;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="destinationUid")
@Getter @ToString
@IdClass(DestinationUid.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DestinationUidEntity {
	
	@Id
	private String uid; //식별자
	@Id
	private String field; // 사용처
	
	@Builder
	public DestinationUidEntity(String uid, String field) {
		this.uid = uid;
		this.field = field;
	}

}
