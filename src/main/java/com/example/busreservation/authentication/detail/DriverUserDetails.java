package com.example.busreservation.authentication.detail;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DriverUserDetails implements UserDetails {
	
	/**
	 *	Auto generated SerialVersion 
	 */
	private static final long serialVersionUID = -3480829057696947222L;
	
	private String driverid;
	private String drivername;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
		return auth;
	}

	@Override
	public String getPassword() {
		return driverid;
	}

	@Override
	public String getUsername() {
		return drivername;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
