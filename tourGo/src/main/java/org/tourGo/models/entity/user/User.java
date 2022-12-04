package org.tourGo.models.entity.user;

import javax.persistence.*;

import lombok.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.user.ActiveType;
import org.tourGo.models.user.UserType;

@Entity
@Getter @Setter
public class User extends BaseEntity {
	
	@Id @GeneratedValue
	private Long userNo;
	
	@Column(unique=true, length=40, nullable=false)
	private String userId;
	
	@Column(length=65, nullable=false)
	private String userPw;
	
	@Column(length=20, nullable=false)
	private String userNm; // 사용자 생일
	
	@Column(length=8, nullable=false)
	private String birth; // 생일
	
	@Column(length=60, nullable=false)
	private String email; // 이메일
	
	@Column(length=12, nullable=false)
	private String mobile; // 휴대전화번호
	
	@Lob
	@Column(nullable=false)
	private String intro; //  자기소개
	
	@Enumerated(EnumType.STRING) 
	@Column(nullable=false)
	private UserType adminType = UserType.USER; // 관리자 권한
	
	@Transient
	@Column(nullable=false, columnDefinition="char(1) default 'N'")
	private String deleteYn; // 삭제 여부
	
	@Enumerated(EnumType.STRING) 
	@Column(length=40, nullable=false)
	private ActiveType activeType = ActiveType.ACTIVE; // 활동 여부
}
