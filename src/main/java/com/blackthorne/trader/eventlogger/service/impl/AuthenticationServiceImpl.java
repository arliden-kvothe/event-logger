package com.blackthorne.trader.eventlogger.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blackthorne.trader.eventlogger.service.AuthenticationService;

/**
 * @author Eduardo Barcenas
 *
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);

	/**
	 * @see com.blackthorne.trader.eventlogger.service.AuthenticationService#login(java.lang.String, java.lang.String)
	 */
	public boolean login(String username, String password) {
		try {
			
			Authentication authenticate = 
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			if (authenticate.isAuthenticated()) {
				
				SecurityContextHolder.getContext().setAuthentication(authenticate);
				//Set<String> authorities = AuthorityUtils.authorityListToSet(authenticate.getAuthorities());
				return true;
			}
		} catch (AuthenticationException e) {
			log.error("authentication error -->", e);
		}
		return false;
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.AuthenticationService#logout()
	 */
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);

	}

}
