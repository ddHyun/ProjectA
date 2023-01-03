package org.tourGo.models.plan;

public enum TourType {
나홀로여행("나홀로여행"),
커플여행("연인/커플"),
친구여행("친구/지인"),
부모님여행("부모님"),
가족여행("가족");

private final String name;

TourType(String name) {
	this.name=name;
}
public String getName() {
	return name;
}
}
