package org.tourGo.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.models.entity.user.QUser;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.ActiveType;
import org.tourGo.models.user.UserRepository;
import org.tourGo.models.user.UserType;

import com.querydsl.core.BooleanBuilder;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 회원 관리
	public Page<User> userManage(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		// USERTYPE이 USER 존재만 갖고오기
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QUser user = QUser.user;
		
		booleanBuilder.and(user.adminType.eq(UserType.USER));
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("userNo")));
		
		return userRepository.findAll(booleanBuilder, pageable);
	}
	
	// 정지/삭제 회원 관리
	public Page<User> userActiveManage(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		return null;
	}
	
	// 관리자 등급 변경(목록)
	public Page<User> adminTypeManage(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		// 활동하는 User만 가져오기
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QUser user = QUser.user;
		
		booleanBuilder.and(user.activeType.eq(ActiveType.ACTIVE));
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("userNo")));
		
		return userRepository.findAll(booleanBuilder, pageable);
	}
}
