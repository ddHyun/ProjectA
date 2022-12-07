package org.tourGo.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tourGo.controller.user.MypageRequest;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class MypageService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void process(MypageRequest request) {
		
		User user = userRepository.findById(request.getUserNo()).orElseThrow(() -> {
			return new IllegalArgumentException("회원이 존재하지 않습니다.");
		});
		
		String rawPassword = request.getUserPwNew();
		String encPassword = encoder.encode(rawPassword);
		
		user.setUserPw(encPassword);
		user.setBirth(request.getBirth());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setIntro(request.getIntro());
		
		userRepository.save(user);
	}
}
