/**
 * 
 */
package com.blackthorne.trader.eventlogger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackthorne.trader.eventlogger.dao.EventDAO;
import com.blackthorne.trader.eventlogger.db.Event;
import com.blackthorne.trader.eventlogger.service.EventService;

/**
 * @author Eduardo Barcenas
 *
 */
@Service("eventService")
@Transactional(readOnly = false)
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;

	/**
	 * @see com.blackthorne.trader.eventlogger.service.EventService#save(com.blackthorne.trader.eventlogger.db.Event)
	 */
	public Integer save(Event t) {
		return eventDAO.save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.EventService#getRows()
	 */
	@Transactional(readOnly = true)
	public List<Event> getRows() {
		return eventDAO.getRows();
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.EventService#update(com.blackthorne.trader.eventlogger.db.Event)
	 */
	public void update(Event t) {
		eventDAO.update(t);

	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.EventService#delete(com.blackthorne.trader.eventlogger.db.Event)
	 */
	public void delete(Event t) {
		eventDAO.delete(t);

	}

}
