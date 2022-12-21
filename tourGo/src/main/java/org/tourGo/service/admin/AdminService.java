package org.tourGo.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.controller.admin.AdminSearchRequest;
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
	
	// 회원 관리(전체)
	public Page<User> userManage(Pageable pageable, AdminSearchRequest request) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		// USERTYPE이 USER 존재만 갖고오기
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QUser user = QUser.user;
		
		String searchType = request.getSearchType();
		String searchKeyword = request.getSearchKeyword();
		
		/* 나중에 싹 다 booleanBuilder -> booleanExpression으로 변경할 예정 */
		if(searchType != null) {
			// 1. 사용자 아이디 검색
			if("userId".equals(searchType)) {
				booleanBuilder.and(user.userId.contains(searchKeyword));
			} 
			// 2. 사용자 이름 검색
			else if("userNm".equals(searchType)){
				booleanBuilder.and(user.userNm.contains(searchKeyword));
			}
			// 3. 전체 검색
			else {
				booleanBuilder.or(user.userId.contains(searchKeyword))
										.or(user.userNm.contains(searchKeyword));
			}
		}
		
		booleanBuilder.and(user.adminType.eq(UserType.USER))
								.and(user.activeType.eq(ActiveType.ACTIVE))
								.and(user.deleteYn.eq("N"));
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("userNo"), Order.desc("regDt")));
		
		return userRepository.findAll(booleanBuilder, pageable);
	}
	
	// 회원 관리(한 대상)
	public Optional<User> userManageView(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	// 정지/삭제 회원 관리
	public Page<User> userActiveManage(Pageable pageable, AdminSearchRequest request) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		// USERTYPE이 USER 존재만 갖고오기
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QUser user = QUser.user;
		
		String searchType = request.getSearchType();
		String searchKeyword = request.getSearchKeyword();
		
		if(searchType != null) {
			// 1. 사용자 아이디 검색
			if("userId".equals(searchType)) {
				booleanBuilder.and(user.userId.contains(searchKeyword));
			} 
			// 2. 사용자 이름 검색
			else if("userNm".equals(searchType)){
				booleanBuilder.and(user.userNm.contains(searchKeyword));
			}
		}
		
		booleanBuilder.or(user.activeType.eq(ActiveType.STOP))
								.or(user.activeType.eq(ActiveType.DORMENT))
								.or(user.deleteYn.eq("Y"));
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("userNo"), Order.desc("regDt")));
		
		return userRepository.findAll(booleanBuilder, pageable);
	}
	
	// 관리자 등급 변경(목록)
	public Page<User> adminTypeManage(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		// 활동하는 User에서 전체관리자만 빼고 가져오기
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QUser user = QUser.user;
		
		booleanBuilder.and(user.activeType.eq(ActiveType.ACTIVE))
								.and(user.adminType.ne(UserType.SUPERADMIN))
								.and(user.deleteYn.eq("N"));;
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("userNo"), Order.desc("regDt")));
		
		return userRepository.findAll(booleanBuilder, pageable);
	}
	
	// 관리자 등급 변경
	public void adminTypeChange(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("회원이 존재하지 않습니다.");
		});
		
		if(user.getAdminType() == UserType.USER) {
			user.setAdminType(UserType.ADMIN);
		} else {
			user.setAdminType(UserType.USER);
		}
		
		userRepository.save(user);
	}
	
	// 활동 상태 변경
	public void activeTypeChange(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("회원이 존재하지 않습니다.");
		});
		
		if(user.getActiveType() == ActiveType.ACTIVE) {
			user.setActiveType(ActiveType.STOP);
		} else {
			user.setActiveType(ActiveType.ACTIVE);
		}
		
		userRepository.save(user);
	}
}
