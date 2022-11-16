package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class ReviewRequest {

	private int reviewNo; 		//글번호
	private String name;		//작성자
	private String reviewTitle;	//제목
	private String region;		//여행지
	private String period;		//기간
	private String reviewContent;	//내용
	private String image;		//사진파일명
	private LocalDateTime reviewRegDt;	//작성일
	private int read;			//조회수
	
	private MultipartFile imageFile;	//실제파일

	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "ReviewRequest [reviewNo=" + reviewNo + ", name=" + name + ", reviewTitle=" + reviewTitle + ", region="
				+ region + ", period=" + period + ", reviewContent=" + reviewContent + ", image=" + image
				+ ", reviewRegDt=" + reviewRegDt + ", read=" + read + "]";
	}
	
}
