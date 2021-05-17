package com.example.busreservation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("driver")
			.password("{noop}Comtec123!")
			.authorities("ROLE_DRIVER");
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

}
