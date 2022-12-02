package org.tourGo.common;

/*
 * 회원가입 Json 데이터 처리
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonResult<T> {
	private boolean success;
	private T data;
	private String message;
}
