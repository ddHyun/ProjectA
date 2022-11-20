package org.tourGo.models.community.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.tourGo.controller.community.review.ReviewRequest;

@Component
public class ReviewDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<ReviewDto> rowMapper = (rs, rowNum) -> {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewNo(rs.getInt("reviewNo"));
		reviewDto.setId(rs.getString("id"));
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
			String sql = "SELECT r.*, (SELECT u.name FROM user_t u WHERE u.id=r.id) FROM review_t r";
			List<ReviewDto> lists = jdbcTemplate.query(sql,rowMapper);
			return lists;
		} catch (Exception e) {
			return null;
		}
	}	

	//글번호로 후기조회
	public ReviewDto getOneReview(int reviewNo) {
		try {//조회결과 없을 땐 return null
			String sql = "SELECT * FROM review_t WHERE reviewNo = ?";
			ReviewDto reviewDto = jdbcTemplate.queryForObject(sql, rowMapper, reviewNo);
			return reviewDto;
		} catch (Exception e) {
			return null;
		}
	}
	
}
