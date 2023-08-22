package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean // IoC가 돼요!
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인해줄대 password를 가로채는데, 해당 passwrod가 뭘로 해쉬되 회원가입 되있는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음!

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/*", "/dummy/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc") 		//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다. 
			.defaultSuccessUrl("/");
		return http.build();
		

	}
}
