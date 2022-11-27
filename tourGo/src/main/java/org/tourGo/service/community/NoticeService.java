package org.tourGo.service.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		list.setName("관리자");
		list.setNoticeContent("공지사항 블라블라블라~~");
		list.setNoticeRead(0);
		list.setRegDt(LocalDateTime.now());
		list.setTitle("공지사항입니다");
		lists.add(list);
		return lists;
	}

}
