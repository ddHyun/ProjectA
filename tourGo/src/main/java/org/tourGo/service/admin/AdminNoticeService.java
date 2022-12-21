package org.tourGo.service.admin;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.controller.admin.AdminNoticeRequest;
import org.tourGo.controller.admin.AdminSearchRequest;
import org.tourGo.models.entity.notice.Notice;
import org.tourGo.models.entity.notice.QNotice;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.notice.NoticeRepository;
import org.tourGo.models.user.UserRepository;

import com.querydsl.core.BooleanBuilder;

@Service
public class AdminNoticeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	private Notice requestToEntity(AdminNoticeRequest request) {
		Notice notice = null;
		
		if(request.getNoticeNo() != null) {
			notice = noticeRepository.findById(request.getNoticeNo()).orElseThrow(() -> {
				return new RuntimeException("해당하는 공지사항이 없습니다.");
			});
		} 
		else {
			notice = new Notice();
		}
		notice.setNoticeTitle(request.getNoticeTitle());
		notice.setNoticeContent(request.getNoticeContent());
		notice.setGid(request.getGid());
		notice.setPostStartDt(request.getPostStartDt());
		notice.setPostEndDt(request.getPostEndDt());
		
		return notice;
	}
	
	public Optional<Notice> findById(Long noticeNo) {
		return noticeRepository.findById(noticeNo);
	}
	
	public Page<Notice> noticeList(Pageable pageable, AdminSearchRequest request) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QNotice notice = QNotice.notice;
		
		String searchType = request.getSearchType();
		String searchKeyword = request.getSearchKeyword();
		
		if(searchType != null) {
			// 1. 제목 검색
			if("title".equals(searchType)) {
				booleanBuilder.and(notice.noticeTitle.contains(searchKeyword));
			} 
			// 2. 내용 검색
			else if("content".equals(searchType)){
				booleanBuilder.and(notice.noticeContent.contains(searchKeyword));
			}
			// 3. 제목+내용 검색
			else if("title_content".equals(searchType)) {
				booleanBuilder.or(notice.noticeTitle.contains(searchKeyword))
										.or(notice.noticeContent.contains(searchKeyword));
			}
			// 4. 작성자 검색
			else if("userId".equals(searchType)) {
				booleanBuilder.and(notice.user.userId.contains(searchKeyword));
			}
		}
		
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("regDt")));
		
		return noticeRepository.findAll(booleanBuilder, pageable);
	}
	
	// 공지사항 등록/수정
	@Transactional
	public void registerNotice(AdminNoticeRequest adminNoticeRequest) {
		
		User user = userRepository.findByUserId(adminNoticeRequest.getUserId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원이 존재하지 않습니다.");
		});
		
		Notice notice = requestToEntity(adminNoticeRequest);
		notice.setUser(user);
			
		noticeRepository.save(notice);
	}
	
}
