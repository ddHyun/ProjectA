package org.tourGo.common;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor
public class JsonErrorResponse {
	private int status;
	private int code;
	private String message;
}
