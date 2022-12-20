package org.tourGo.service.community.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tourGo.controller.community.notice.NoticeRequest;

@Service
public class NoticeService {

	//전체목록 조회
	public List<NoticeRequest> getAllLists() {
		List<NoticeRequest> lists = new ArrayList<>();
		NoticeRequest list = new NoticeRequest();
		list.setNoticeNo(1);
		list.setId("admin");
		list.setNoticeContent("공지사항 블라블라블라~~");
		list.setNoticeRead(0);
		list.setRegDt(LocalDateTime.now());
		list.setTitle("공지사항입니다");
		lists.add(list);
		
		NoticeRequest list2 = new NoticeRequest();
		list2.setNoticeNo(2);
		list2.setId("admin");
		list2.setNoticeContent("또 다른 공지이지요!");
		list2.setNoticeRead(0);
		list2.setRegDt(LocalDateTime.now());
		list2.setTitle("업데이트 안내");
		lists.add(list2);
		
		return lists;
	}

	//글번호로 내용 조회
	public NoticeRequest getOneList(int noticeNo) {
		List<NoticeRequest> lists = getAllLists();
		NoticeRequest list = new NoticeRequest();
		for (int i = 0; i < lists.size(); i++) {
			if(i==noticeNo) {
				list.setId(lists.get(i).getId());
				list.setNoticeContent(lists.get(i).getNoticeContent());
				list.setNoticeNo(lists.get(i).getNoticeNo());
				list.setNoticeRead(lists.get(i).getNoticeRead());
				list.setRegDt(lists.get(i).getRegDt());
				list.setTitle(lists.get(i).getTitle());
				break;
			}
		}
		return list;
	}

	//조회수 증가
	public boolean updateNoticeRead(int noticeNo) {
//		noticeRepository.updateNoticeRead(noticeNo);
		return false;
	}

}
