package org.tourGo.models.community.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	
	//DB 데이터를 DTO에 저장하는 메서드
	private RowMapper<ReviewDto> rowMapper = (rs, rowNum) -> {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewNo(rs.getInt("reviewNo"));
		reviewDto.setId(rs.getString("id"));
		reviewDto.setName(rs.getString("name"));
		reviewDto.setImage(rs.getString("image"));
		reviewDto.setPeriod(rs.getString("period"));
		reviewDto.setRegion(rs.getString("region"));
		reviewDto.setReviewContent(rs.getString("reviewContent"));
		reviewDto.setReviewRead(rs.getInt("reviewRead"));
		reviewDto.setReviewTitle(rs.getString("reviewTitle"));
		reviewDto.setReviewRegDt(rs.getTimestamp("reviewRegDt").toLocalDateTime());
		return reviewDto;
	};	
	
	//후기 전체목록 조회
	public List<ReviewDto> getAllReviewList() {
		try {
			String sql = "SELECT r.*, u.name FROM review_t r LEFT JOIN user_t u on r.id=u.id";
			List<ReviewDto> lists = jdbcTemplate.query(sql,rowMapper);
			return lists;
		} catch (Exception e) {
			return null;
		}
	}	
	
}
