package org.tourGo.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tourGo.controller.user.SignRequest;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class SignService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public void process(SignRequest request) {
		
		String encryptPw = encoder.encode(request.getUserPw());
		String expMobile = request.getMobile().replaceAll("\\D", ""); // 숫자가 아닌 문자를 제거 -> 숫자
		
		User user = new User();
		user.setUserId(request.getUserId());
		user.setUserPw(encryptPw);
		user.setUserNm(request.getUserNm());
		user.setBirth(request.getBirth());
		user.setEmail(request.getEmail());
		user.setMobile(expMobile);
		user.setIntro(request.getIntro());
		
		userRepository.save(user);
	}
}
