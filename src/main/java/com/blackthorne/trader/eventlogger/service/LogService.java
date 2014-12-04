package com.blackthorne.trader.eventlogger.service;

import java.util.Collection;
import java.util.List;

import com.blackthorne.trader.eventlogger.db.Log;

/**
 * @author Eduardo Barcenas
 *
 */
public interface LogService {
	
	Integer save(Log t);

	List<Log> getRows();

	void update(Log t);

	void delete(Log t);

	List<Log> getAdminReviews();

	Log get(Integer id);
	
	List<Log> getRowsByGroups(Collection<String> groups);
	
}
