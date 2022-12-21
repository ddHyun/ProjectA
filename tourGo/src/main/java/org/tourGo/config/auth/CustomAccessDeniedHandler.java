package org.tourGo.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		// 허용하지 않은 권한 접근으로 인한 에러 상태일 때
		if(accessDeniedException instanceof AccessDeniedException) {
			// 현재 권한 정보
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			// 1. PrincipalDetail 권한으로 접근한 경우
			// 원래 ((PrincipalDetail) authentication.getPrincipal()).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))
			// 위 코드로 권한도 확인해야하는데 여기서 체크가 안된다. 아마 hasAnyRole에서 체크하여 넘어오지 않는걸로 판단, 나중에 한번 체크해보자
			if(authentication != null) {
				String url = request.isUserInRole("ROLE_USER") == true ? "/main/main_view" : "/admin/index";
				request.setAttribute("msg", "접근권한 없는 사용자입니다.");
				request.setAttribute("nextPage", url);
			} 
			// 2. 비로그인 상태에서 접근한 경우
			else {
				request.setAttribute("msg", "로그인 권한이 없는 아이디입니다.");
				request.setAttribute("nextPage", "/user/login");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				SecurityContextHolder.clearContext();
			}
		} else {
			
		}
		request.getRequestDispatcher("/error/denied").forward(request, response);
	}
	
}
