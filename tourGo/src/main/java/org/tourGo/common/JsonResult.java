package org.tourGo.common;

import lombok.*;

/**
 * JSON 데이터 
 * @author user
 *
 * @param <T>
 */

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
	private boolean success;
	private String message;
	private T data;
}
