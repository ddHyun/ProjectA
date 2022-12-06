package org.tourGo.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepository userRepository;
}
