package com.brquickpoll;

import javax.inject.Inject; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject 
	private UserDetailsService userDetailsService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
			 	.antMatchers("/v1/**", "/v2/**", "/swagger-ui/**", "/api-docs/**").permitAll()
			 	.antMatchers("/v3/polls/ **").authenticated()
			 	.and()
			.httpBasic()
				.realmName("BrQuick Poll")
				.and()
			.csrf()
				.disable();
	}
}
