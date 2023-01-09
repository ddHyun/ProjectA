package org.tourGo.models.entity.community.review;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="liked")
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LikedEntity {

	@Id
	private String uid; // 327_111 -> like 327_% 

	@Builder
	public LikedEntity(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "LikedEntity [uid=" + uid + "]";
	}
}
