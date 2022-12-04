package org.tourGo.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.tourGo.controller.user.LoginRequest;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepository userRepository;
	
	public void process(LoginRequest loginRequest, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		String userId = loginRequest.getUserId();
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			throw new RuntimeException("가입되지 않은 아이디 입니다.");
		}
		
		String userPw = loginRequest.getUserPw();
		// boolean matched = BCrypt.checkpw(memPw, member.getMemPw());
		// if(!matched) {
		// 	throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		// }
		if(!userPw.equals(user.getUserPw())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		session.setAttribute("user", user);
	}
}
