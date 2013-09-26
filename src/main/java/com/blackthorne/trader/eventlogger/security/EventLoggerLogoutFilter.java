package com.blackthorne.trader.eventlogger.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * @author Eduardo Barcenas
 *
 */
public class EventLoggerLogoutFilter extends LogoutFilter {
	
	private static final Logger log = Logger.getLogger(EventLoggerLogoutFilter.class); 

	public EventLoggerLogoutFilter(String logoutSuccessUrl, LogoutHandler[] handlers) {
		super(logoutSuccessUrl, handlers);
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		super.doFilter(arg0, arg1, arg2);
	}

}
