package org.tourGo.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * 예외에 대한 응답정보를 저장하는 클래스
 * message : 에러에 대한 메시지 작성
 * status : http status code 작성
 * error : 요청값에 대한 field, value, reason 작성
 *   field : 검증 실패한 커맨드 객체 필드명
 *   value : 파라미터로 들어온 값
 *   reason : 오류문구
 * code : 에러에 할당되는 유니크한 코드값
 */

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private String message;
	private HttpStatus status;
	private String code;
	
	public ErrorResponse(ErrorCode errorCode) {
		this.message = errorCode.getMessage();
		this.code = errorCode.getCode();
		this.status = errorCode.getStatus();
	}
	
}
