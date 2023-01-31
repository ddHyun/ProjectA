package org.tourGo.models.plan.entity.like;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanUidRequest {
	
	private String uid; // 식별자
	private String field; // 사용처
	
	public PlanUidRequest(PlanUidEntity planUidEntity) {
		this.uid = planUidEntity.getUid();
		this.field = planUidEntity.getField();
	}
	public static String getUid(Long boardNo, Long userNo) {
		//Request서블릿객체 가져오기
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
		
		//요청헤더의 user-agent와 브라우저 ip를 합쳐 hashCode만들기
		String userAgent = httpServletRequest.getHeader("User-Agent");
		String userIP = httpServletRequest.getRemoteAddr();
		int hash = (userAgent+userIP).hashCode(); // user-agent와 ip 동일여부 파악용
		String uid = "";
		
		if(userNo != null) { //로그인일 경우 userNo 포함
			uid = boardNo + "_" + hash + "_" + userNo;
		} else {
			uid = boardNo + "_" + hash + "_" + 0;
		}
		return uid;
		
	}
	@Override
	public String toString() {
		return "PlanUidRequest [uid=" + uid + ", field=" + field + "]";
	}
	
}
