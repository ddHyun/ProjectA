package org.tourGo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링 시큐리티가 로그인 요청을 가로챌 때 username(userId), password(userPw) 변수를 가로채는데
	// password 처리는 자동으로 하므로 username이 DB에 있는지만 확인
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User principal = userRepository.findByUserId(userId)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + userId);
				});
		return new PrincipalDetail(principal); // 스프링 시큐리티 세션에 유저 정보가 저장
	}
}
