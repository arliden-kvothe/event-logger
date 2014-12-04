package com.blackthorne.trader.eventlogger.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.blackthorne.trader.eventlogger.service.AuthenticationService;

/**
 * @author Eduardo Barcenas
 *
 */
@ManagedBean(name = "authenticationBean")
@RequestScoped
public class AuthenticationBean implements Serializable {
	
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 7701222798838739732L;
	
	private String username = "";
	private String password = "";
	private boolean rememberMe = false;
	private boolean loggedIn = false;
	
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;

	public String login() throws IOException, ServletException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext extenalContext = facesContext.getExternalContext();
	    RequestDispatcher dispatcher = ((ServletRequest)extenalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
	    dispatcher.forward((ServletRequest)extenalContext.getRequest(), (ServletResponse)extenalContext.getResponse());
	    facesContext.responseComplete();
	    return null;
	}
	
	public String logout() {
		return null;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(final boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the authenticationService
	 */
	public final AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService the authenticationService to set
	 */
	public final void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
