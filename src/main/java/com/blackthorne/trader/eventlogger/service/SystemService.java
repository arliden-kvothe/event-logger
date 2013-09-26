/**
 * 
 */
package com.blackthorne.trader.eventlogger.service;

import java.util.List;

import com.blackthorne.trader.eventlogger.db.System;

/**
 * @author martinni
 *
 */
public interface SystemService {
	
	Integer save(System t);

	List<System> getRows();

	void update(System t);

	void delete(System t);

	System get(Integer id);

}
