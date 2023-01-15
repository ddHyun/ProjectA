package org.tourGo.controller.community.review;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tourGo.models.entity.community.review.UidEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UidRequest {

	private String uid;		//식별자
	private String field;	//사용처
	
	public UidRequest(UidEntity entity) {
		uid = entity.getUid();
		field = entity.getField();
	}	
	
	//uid 만들기(좋아요&조회수 등의 식별자로 사용)
	public static String getUid(Long boardNo, Long userNo) {
		//Request서블릿객체 가져오기
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		
		//요청헤더의 user-agent와 브라우저 ip를 합쳐 hashCode만들기
		String userAgent = request.getHeader("User-Agent");
		String userIP = request.getRemoteAddr();
		int hash = (userAgent+userIP).hashCode(); //user-agent와 ip 동일여부 파악용
		String uid = "";
		
		if(userNo != null) {//로그인일 경우 userNo 포함
			uid = boardNo + "_" + hash + "_" + userNo;
		}else {//비로그인(비회원포함)일 경우 userNo에 0
			uid = boardNo + "_" + hash + "_" + 0;
		}

		return uid;
	}

	@Override
	public String toString() {
		return "UidRequest [uid=" + uid + ", field=" + field + "]";
	}
}