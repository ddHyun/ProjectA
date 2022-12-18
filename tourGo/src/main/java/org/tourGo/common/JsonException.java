package org.tourGo.common;

/**
 * JSON 전용 예외 
 * 
 * @author user
 *
 */
public class JsonException extends RuntimeException {
	
	private int status;
	private int code;
	
	public JsonException(String message) {
		this(message, 500); // 응답 상태값은 기본 값으로 500
	}
	
	public JsonException(String message, int status) {
		this(message, status, 0);
	}
	
	public JsonException(String message, int status, int code) {
		super(message);
		this.status = status;
		this.code = code;
	}
	
	public int getStatus() {
		return status;
	}
	
	public int getCode() {
		return code;
	}
	
	public JsonErrorResponse getResponse() {
		return new JsonErrorResponse(status, code, getMessage());
		
	}
}
