package com.example.busreservation.authentication.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.busreservation.authentication.detail.DriverUserDetails;
import com.example.busreservation.service.DriverService;

public class DriverAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DriverService service;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		Authentication result;
		Optional<DriverUserDetails> optDriver = service.getDriver(authentication.getCredentials().toString(), authentication.getPrincipal().toString());
		
		if (optDriver.isPresent()) {
			DriverUserDetails driver = optDriver.get();
			result = new UsernamePasswordAuthenticationToken(driver.getUsername(), driver.getPassword(), driver.getAuthorities());
		} else {
			throw new BadCredentialsException("입력값 검증 실패");
		}
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
