package org.tourGo.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean register(User user) {
		String sql = "INSERT INTO user(userId, userPw, userNm, birth, email, mobile, intro) " +
							"VALUES(?, ?, ? ,? ,?, ?, ?)";
		
		int affectedRows = jdbcTemplate.update(sql, 
																user.getUserId(),
																user.getUserPw(),
																user.getUserNm(),
																user.getBirth(),
																user.getEmail(),
																user.getMobile(),
																user.getIntro());
		
		return affectedRows > 0;
	}
}
