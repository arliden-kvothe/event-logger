package com.blackthorne.trader.eventlogger.service;


/**
 * @author Eduardo Barcenas
 *
 */
public interface AuthenticationService {
	
	boolean login(String username, String password);
	
	void logout();
}
