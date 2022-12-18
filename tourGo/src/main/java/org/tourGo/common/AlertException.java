package org.tourGo.common;

public class AlertException  extends RuntimeException {
	
	// 자바스크립트 추가 동작이 있는 경우 
	//reload 또는 이동할 url
	private String action = null;
	
	private String target = "self";
	
	public AlertException(String message) {
		this(message, "self", null);
	}
	
	public AlertException(String message, String action) {
		this(message, "self", action);
		
	}
	
	public AlertException(String message, String target, String action) {
		super(message);
		this.action = action;
		this.target = target;
	}
	
	public String getAction() {
		return action;
	}
	
	public String getTarget() {
		return target;
	}
}
