package org.tourGo.models.community.review;

import java.time.LocalDateTime;

public class ReviewDto {

	private int reviewNo;
	private String id;
	private String name;//테이블 컬럼이 없어도 rowMapping해줄 때 유의하면 변수 사용 가능
	private String reviewTitle;
	private String region;
	private String period;
	private String reviewContent;
	private String image;
	private LocalDateTime reviewRegDt;
	private int reviewRead;
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public LocalDateTime getReviewRegDt() {
		return reviewRegDt;
	}
	public void setReviewRegDt(LocalDateTime reviewRegDt) {
		this.reviewRegDt = reviewRegDt;
	}
	public int getReviewRead() {
		return reviewRead;
	}
	public void setReviewRead(int reviewRead) {
		this.reviewRead = reviewRead;
	}
	
	
	@Override
	public String toString() {
		return "ReviewDto [reviewNo=" + reviewNo + ", id=" + id + ", name=" + name + ", reviewTitle=" + reviewTitle
				+ ", region=" + region + ", period=" + period + ", reviewContent=" + reviewContent + ", image=" + image
				+ ", reviewRegDt=" + reviewRegDt + ", reviewRead=" + reviewRead + "]";
	}	
	
}
