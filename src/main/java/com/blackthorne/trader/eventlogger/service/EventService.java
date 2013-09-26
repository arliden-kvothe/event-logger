/**
 * 
 */
package com.blackthorne.trader.eventlogger.service;

import java.util.List;

import com.blackthorne.trader.eventlogger.db.Event;

/**
 * @author martinni
 *
 */
public interface EventService {
	
	Integer save(Event t);

	List<Event> getRows();

	void update(Event t);

	void delete(Event t);

}
