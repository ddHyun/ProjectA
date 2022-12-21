package org.tourGo.service.community.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.tourGo.controller.community.notice.NoticeRequest;
import org.tourGo.controller.community.notice.SearchRequest;
import org.tourGo.models.entity.notice.Notice;
import org.tourGo.models.entity.notice.QNotice;
import org.tourGo.models.notice.NoticeRepository;

import com.querydsl.core.BooleanBuilder;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	//전체목록 조회
	public Page<Notice> noticeList(Pageable pageable, SearchRequest searchRequest) {
		
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
				
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QNotice notice = QNotice.notice;
		
		String searchType = searchRequest.getSearchType();
		String searchKeyword = searchRequest.getSearchKeyword();
		
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
		
		// 삭제되지 않은 공지사항만 출력(일단 삭제 기능은 아직 없음)
		booleanBuilder.and(notice.deleteYn.eq('N'));
		
		pageable = PageRequest.of(page, 10, Sort.by(Order.desc("regDt")));
		
		return noticeRepository.findAll(booleanBuilder, pageable);
	}

	//글번호로 내용 조회
	public Optional<Notice> findById(Long noticeNo) {
		return noticeRepository.findById(noticeNo);
	}

	//조회수 증가
	public boolean updateNoticeRead(int noticeNo) {
//		noticeRepository.updateNoticeRead(noticeNo);
		return false;
	}

}
