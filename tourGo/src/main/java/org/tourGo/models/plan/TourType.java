package org.tourGo.models.plan;

public enum TourType {
SOLO("나홀로여행"),
COUPLE("연인/커플"),
FRIENDS("친구/지인"),
PARENTS("부모님"),
FAMILY("가족");

private String name;

TourType(String name) {
	this.name=name;
}
public String getName() {
	return name;
}
}
