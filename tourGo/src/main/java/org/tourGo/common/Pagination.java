package org.tourGo.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.*;

@Getter
@Setter
@ToString
public class Pagination<T> {

	private int page; 		//현재 페이지
	private long total;		//전체 레코드 수
	private int prev;		//전 구간 마지막 페이지
	private int next;		//다음 구간 마지막 페이지
	private int lastPage;	//마지막 페이지
	private int pageCnt;	//구간별 페이지 개수
	private int pagePerCnt;	//한 페이지당 레코드 수
	private boolean isFirstCnt;	//첫번째 구간 여부
	private boolean isLastCnt;	//마지막 구간 여부
	private List<Integer> pages;//구간별 페이지 번호
	private String baseUrl;
	
	public Pagination(Page<T> data, String url) {
		this(data.getPageable().getPageNumber()+1, data.getTotalElements(), data.getSize(), url);
	}
	
	public Pagination(int page, long total, String url) {
		this(page, total, 20, url);
	}
	
	public Pagination(int _page, long _total, int _pagePerCnt, String url) {
		total = _total;
		pagePerCnt = _pagePerCnt;
		page = _page <= 0? 1 : _page; //현재페이지가 1보다 작으면 1로 세팅
		
		if(total < 1) { //총레코드 수가 0이면 리턴
			return;
		}
		
		baseUrl = url;
		baseUrl = baseUrl==null? "" : baseUrl;
		
		this.pageCnt = pageCnt < 1 ? 10 : pageCnt; //페이지 구간이 0이면 10으로 세팅
		this.lastPage = (int)Math.ceil(total/(double)this.pagePerCnt);//마지막 페이지=총레코드수/한페이지당 레코드수
		
		page = page < 1 ? 1 : page;
		if(page > this.lastPage) { //페이지가 마지막페이지보다 크면 마지막페이지로 세팅
			page = this.lastPage;
		}
		
		pages = new ArrayList<>();
		
		//페이지 구간 구하기
		int cnt = (int)Math.ceil(this.page/(double)this.pageCnt) - 1; //현재 페이지 구간 번호
		int lastCnt = (int)Math.ceil(this.lastPage/(double)this.pageCnt) - 1; //마지막 페이지 구간 번호
		
		if(cnt==0) {//현재페이지 구간번호가 0이면 첫번째 구간 표시
			this.isFirstCnt=true;
		}
		if(cnt==lastCnt) {//현재페이지 구간번호가 마지막페이지 구간번호와 동일하면 마지막 페이지 구간 표시
			this.isLastCnt=true;
		}
		
		//구간별 페이지 번호
		int start = cnt * this.pageCnt + 1;
		for(int i=start; i<start+this.pageCnt; i++) {
			pages.add(i);
			
			if(i==this.lastPage) {
				break; //배열에 페이지 번호를 담되 마지막페이지면 스탑
			}
		}
		
		//전 구간 마지막 페이지
		if(!this.isFirstCnt) {
			prev = cnt * this.pageCnt;
		}
		
		//다음 구간 시작 페이지
		if(!this.isLastCnt) {
			next = (cnt+1) * this.pageCnt + 1;
		}
	}
}
