package com.blackthorne.trader.eventlogger.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * @author Eduardo Barcenas
 *
 */
public class EventLoggerLogoutHandlerFilter implements LogoutHandler {
	
	private static final Logger log = Logger.getLogger(EventLoggerLogoutHandlerFilter.class);

	/**
	 * @see org.springframework.security.web.authentication.logout.LogoutHandler#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			log.debug("cookie = [" + ToStringBuilder.reflectionToString(cookie, ToStringStyle.MULTI_LINE_STYLE) + "]");
			cookie.setPath(request.getContextPath());
	        cookie.setMaxAge(0);
	        response.addCookie(cookie);
		}
		
		// forcing
		Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        SecurityContextHolder.getContext().setAuthentication(null);

	}

}
