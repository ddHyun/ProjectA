package org.tourGo.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 사용자 확인(유저 번호)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	// 사용자 확인(유저 아이디)
	public Optional<User> findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
}
