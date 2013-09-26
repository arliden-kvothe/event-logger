package com.blackthorne.trader.eventlogger.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.blackthorne.trader.eventlogger.auth.UserRole;

/**
 * @author Eduardo Barcenas
 *
 */
public class EventLoggerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger log = Logger.getLogger(EventLoggerAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		Set<String> authorities = AuthorityUtils.authorityListToSet(authenticate.getAuthorities());
		String home;
		if (authorities.contains(UserRole.ROLE_ADMIN.toString())) {
			home = "/admin.xhtml";
		} else {
			home = "/user.xhtml";
		}
		getRedirectStrategy().sendRedirect(request, response, home);
	}

}
