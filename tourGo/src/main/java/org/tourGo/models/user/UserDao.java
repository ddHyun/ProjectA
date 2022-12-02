package org.tourGo.models.user;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> rowMapper = (rs, i) -> {
		User user = new User();
		
		user.setUserNo(rs.getLong("userNo"));
		user.setUserId(rs.getString("userId"));
		user.setUserPw(rs.getString("userPw"));
		user.setUserNm(rs.getString("userNm"));
		user.setEmail(rs.getString("email"));
		user.setMobile(rs.getString("mobile"));
		user.setRegDt(rs.getTimestamp("regDt").toLocalDateTime());
		Timestamp modDt = rs.getTimestamp("modDt");
		if(modDt != null) {
			user.setModDt(modDt.toLocalDateTime());
		}
		return user;
	};
	
	public boolean register(User user) {
		String sql = "INSERT INTO user(userId, userPw, userNm, birth, email, mobile, intro) " +
							"VALUES(?, ?, ? ,? ,?, ?, ?)";
		
		String mobile = null;
		String _mobile = user.getMobile();
		if(_mobile != null) {
			mobile = _mobile.replaceAll("\\D", "");
		}
		
		int affectedRows = jdbcTemplate.update(sql, 
																user.getUserId(),
																user.getUserPw(),
																user.getUserNm(),
																user.getBirth(),
																user.getEmail(),
																mobile,
																user.getIntro());
		
		return affectedRows > 0;
	}
	
	public User get(String userId) {
		try {
			
			String sql = "SELECT * FROM user WHERE userId = ?";
			User _user = 
					jdbcTemplate.queryForObject(sql, rowMapper, userId);
			
			if(_user == null) {
				throw new RuntimeException("회원 없음!");
			}
			
			return _user;
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw e;
			}
			return null;
		}
	}
	
	public List<User> gets() {
		List<User> users = jdbcTemplate.query("SELECT * FROM user", rowMapper);
		return users;
	}
}
