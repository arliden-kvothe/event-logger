package com.blackthorne.trader.eventlogger.auth;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Eduardo Barcenas
 * 
 */
public class User implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -8713089915725926581L;

	private String username;

	private String password;
	
	private Set<String> roles;
	
	public User() {
		
	}
	
	public User(String username, String password, Set<String> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the roles
	 */
	public final Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public final void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Boolean hasRole(String role) {
		if (role != null) {
			return roles.contains(role);
		}
		return false;
	}


}
