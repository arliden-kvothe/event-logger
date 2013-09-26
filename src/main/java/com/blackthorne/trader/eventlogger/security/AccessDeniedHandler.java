package com.blackthorne.trader.eventlogger.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.blackthorne.trader.eventlogger.auth.UserRole;

/**
 * @author Eduardo Barcenas
 * 
 */
@Component("eventLoggerAccessDeniedHandler")
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	public AccessDeniedHandler() {

	}

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {

		String url;

		Set<String> authorities = 
				AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		if (authorities.contains(UserRole.ROLE_ADMIN.toString())) {
			url = request.getContextPath() + "/admin.xhtml";
		} else {
			url = request.getContextPath() + "/user.xhtml";
		}

		request.getSession().setAttribute("url", url);

		response.sendRedirect(request.getContextPath() + "/access-denied.xhtml");

	}

}
