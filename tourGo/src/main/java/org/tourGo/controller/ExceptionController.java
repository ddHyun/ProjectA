package org.tourGo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tourGo.common.AlertException;

@ControllerAdvice("org.tourGo.controller")
public class ExceptionController {
//	
//	@ResponseBody
//	@ExceptionHandler(JsonException.class)
//	public ResponseEntity<JsonErrorResponse> jsonErrorHandler(JsonException e) {
//		JsonErrorResponse jer = e.getResponse();
//		
//		return ResponseEntity.status(jer.getStatus()).body(jer);
//	}
	
	/*
	 * 해당 오류메시지를 alert창에 띄울 때 사용하는 예외
	 */
	@ExceptionHandler(AlertException.class)
	public String handleAlertException(AlertException e, Model model) {
		
		String action = e.getAction();
		//부모창에 추가동작을 줄 땐 target을 'parent'로
		String target = e.getTarget() == null ? "self":e.getTarget();

		String script = "alert('"+e.getMessage()+"');";
		if (action != null) {
			if (action.equals("reload")) { // reload라면 새로고침
				script += target + ".location.reload();"; 
			} else if (action.equals("back")) {
				script += target + ".history.back();"; 
			} else { // 그외는 페이지 이동
				script += target + ".location.replace('" + action + "');";
			}
		}
		e.printStackTrace();
		model.addAttribute("script", script);
		
		return "common/execution_script";
	
	}	
	
	/*
	 * 미지정 RuntimeException 처리 
	 */
	//예외 처리하기
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e, Model model) {
		
		model.addAttribute("message", e.getMessage());
		e.printStackTrace();
		
		return "common/errors"; 
	}
	
}
