package org.tourGo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tourGo.config.auth.CustomAccessDeniedHandler;
import org.tourGo.config.auth.CustomAuthenticationFailureHandler;
import org.tourGo.config.auth.CustomAuthenticationSuccessHandler;
import org.tourGo.config.auth.PrincipalDetailService;


@Configuration // 설정 파일을 빈 등록
@EnableWebSecurity // 스프링 시큐리티의 설정을 해당 파일에서 관리
@EnableGlobalMethodSecurity(prePostEnabled=true) // 특정 주소로 접근 시 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler) {
		this.customAccessDeniedHandler = customAccessDeniedHandler;
	}
	
	// 비밀번호 인코딩
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()
				.antMatchers("/community/review_register").authenticated()
				.antMatchers("/admin/user/adminTypeManage").hasRole("SUPERADMIN")
				.antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
				.antMatchers("/user/mypage").hasRole("USER")
				.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.antMatchers("/main/**").permitAll()

			.and()
				.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/loginProc") // 스프링 시큐리티가 해당 주소로 오는 로그인 요청을 가로채서 대신 로그인 수행
				.usernameParameter("userId") // 아이디 파라미터명 설정, default : username
				.passwordParameter("userPw") // 비밀번호 파라미터명 설정, default : password
				.defaultSuccessUrl("/user/loginRedirect")
				.failureHandler(customAuthenticationFailureHandler); // 로그인 실패시 핸들러 적용
		
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/user/login")
			.deleteCookies("JSESSIONID", "remember-me");
		
		http
			.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
		//모달 팜업창 관련
		http.headers().frameOptions().disable(); // Spring Security 비활성화
	}
}
