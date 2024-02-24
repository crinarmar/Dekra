package com.dekra.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();

	}
}
