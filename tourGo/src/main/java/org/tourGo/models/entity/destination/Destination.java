package org.tourGo.models.entity.destination;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.catalina.User;
import org.tourGo.common.BaseEntity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Destination extends BaseEntity {
	@Id @GeneratedValue
	private Long DestinationNo;// db테이블 증감번호


	@Column(nullable = true)
	private String image;// 관광지 이미지
	private String name;// 관광지이름
	private String address;// 관광지 주소
	
}
