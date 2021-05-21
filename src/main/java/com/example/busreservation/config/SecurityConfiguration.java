package com.example.busreservation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.busreservation.authentication.provider.DriverAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(driverAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.antMatchers("/driver")
				.access("hasRole('ROLE_DRIVER')")
			.and()
			.csrf()
				.ignoringAntMatchers("/h2-console/**")
			.and()
			.headers()
				.frameOptions()
				.disable()
			.and()
			.formLogin();
	}
	
	/**
	 * 기사 사이트 로그인 제공
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationProvider driverAuthenticationProvider() {
		return new DriverAuthenticationProvider();
	}

}
