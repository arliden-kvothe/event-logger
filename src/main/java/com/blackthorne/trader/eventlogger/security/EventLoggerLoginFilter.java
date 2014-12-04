package com.blackthorne.trader.eventlogger.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import com.blackthorne.trader.eventlogger.auth.UserRole;

/**
 * @author Eduardo Barcenas
 * 
 */
public class EventLoggerLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger log = Logger.getLogger(EventLoggerLoginFilter.class);

	/**
	 * 
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		log.debug("--- saved url: "
				+ (null != defaultSavedRequest ? defaultSavedRequest.getRequestURL() : "NULL"));
		
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (null != authenticate && authenticate.isAuthenticated()) {
			if (request.getRequestURL().toString().contains("login") || request.getRequestURI().endsWith("/")) {
				log.debug("user already authenticated!");
				Set<String> authorities = AuthorityUtils.authorityListToSet(authenticate.getAuthorities());
				String home;
				if (authorities.contains(UserRole.EVTLOG_OPERATOR.toString())) {
					home = "/user.xhtml";
				} else {
					home = "/admin.xhtml";
				}
				response.sendRedirect(request.getContextPath() + home);
				return;
			}
		}
		
		super.doFilter(req, res, chain);
	}
	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request,
//			HttpServletResponse response) throws AuthenticationException {
//		log.debug("attempt authentication...");
//		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
//		if (null != authenticate && authenticate.isAuthenticated()) {
//			log.info("User authenticated successfully");
//			return authenticate;
//		}
//		return super.attemptAuthentication(request, response);
//	}
//	
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request,
//			HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		log.debug("successful authentication!");
//		super.successfulAuthentication(request, response, chain, authResult);
//	}
//	
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request,
//			HttpServletResponse response, AuthenticationException failed)
//			throws IOException, ServletException {
//		log.debug("authentication failed!");
//		super.unsuccessfulAuthentication(request, response, failed);
//	}

}
