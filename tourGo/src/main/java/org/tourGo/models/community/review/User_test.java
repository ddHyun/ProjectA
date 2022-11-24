package org.tourGo.models.community.review;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="user_test")
@Getter @Setter @ToString
public class User_test {

	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idx;
	@Id
	@Column(nullable=false, length=45, unique=true)
	private String id;
	@Column(nullable=false, length=45)
	private String name;
}
