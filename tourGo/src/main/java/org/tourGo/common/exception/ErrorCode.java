package org.tourGo.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	//common
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "입력한 값이 유효하지 않습니다"),
	METHOD_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "C002", "Invalid Input Value"),
	HANDLE_ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "C003", "Access is Denied"),
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C004", "Resouece Not Exists"),
	DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "C005", "조회 결과가 없습니다"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C006", "Internal Server Error"),
	
	//파트별 오류코드
	
	;
	
	private final HttpStatus status;
	private final String code;
	private final String message;
	
	 private ErrorCode(HttpStatus status, String code, String message){
		this.status = status;
		this.code = code;
		this.message = message;
		
	}
}
