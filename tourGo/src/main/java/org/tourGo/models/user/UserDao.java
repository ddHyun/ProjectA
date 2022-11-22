package org.tourGo.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean register(UserDto user) {
		String sql = "INSERT INTO user(userId, userPw, userNm, birth, email, mobile, intro) " +
							"VALUES(?, ?, ? ,? ,?, ?, ?)";
		
		int affectedRows = jdbcTemplate.update(sql, 
																user.getUId(),
																user.getUPw(),
																user.getUNm(),
																user.getBirth(),
																user.getEmail(),
																user.getMobile());
		
		return affectedRows > 0;
	}
}
