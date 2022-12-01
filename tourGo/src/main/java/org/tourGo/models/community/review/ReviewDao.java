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
//		reviewDto.setId(rs.getString("id"));
//		reviewDto.setName(rs.getString("name"));
//		reviewDto.setFileName(rs.getString("fileName"));
		reviewDto.setPeriod(rs.getString("period"));
		reviewDto.setRegion(rs.getString("region"));
		reviewDto.setReviewContent(rs.getString("reviewContent"));
		reviewDto.setReviewRead(rs.getInt("reviewRead"));
		reviewDto.setReviewTitle(rs.getString("reviewTitle"));
		reviewDto.setRegDt(rs.getTimestamp("reviewRegDt").toLocalDateTime());
		return reviewDto;
	};	
	
	//후기 전체목록 조회
	public List<ReviewDto> getAllReviewList() {
		try {
			String sql = "SELECT * FROM review_v";
			List<ReviewDto> lists = jdbcTemplate.query(sql,rowMapper);
			return lists;
		} catch (Exception e) {
			return null;
		}
	}	
	
	//검색어로 조회
	public List<ReviewDto> searchList(String search){
		try {
			String sql = "SELECT * FROM review_v WHERE region LIKE ?";
			String likeSearch = "%"+search+"%";
			List<ReviewDto> lists = jdbcTemplate.query(sql, rowMapper, likeSearch);
			
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	// 한 가지 목록 조회
	public ReviewDto getOneReviewList(int reviewNo) {
		String sql = "SELECT * FROM review_v WHERE reviewNo=?";
		ReviewDto list = jdbcTemplate.queryForObject(sql, rowMapper, reviewNo);
		
		return list;
	}
	
}
