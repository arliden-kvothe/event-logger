package com.blackthorne.trader.eventlogger.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackthorne.trader.eventlogger.dao.EventDAO;
import com.blackthorne.trader.eventlogger.dao.LogDAO;
import com.blackthorne.trader.eventlogger.dao.SystemDAO;
import com.blackthorne.trader.eventlogger.db.Log;
import com.blackthorne.trader.eventlogger.service.LogService;

/**
 * @author Eduardo Barcenas
 *
 */
@Service("logService")
@Transactional(readOnly = false)
public class LogServiceImpl implements LogService {	

	@Autowired
	private LogDAO logDAO;
	@Autowired
	private EventDAO eventDAO;
	@Autowired
	private SystemDAO systemDAO;
	
	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#save(com.blackthorne.trader.eventlogger.db.Log)
	 */
	public Integer save(Log t) {
		t.setEvent(eventDAO.get(t.getEventId()));
		t.setSystem(systemDAO.get(t.getSystemId()));
		t.setDateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
		return logDAO.save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#getRows()
	 */
	@Transactional(readOnly = true)
	public List<Log> getRows() {
		return logDAO.getRows();
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#update(com.blackthorne.trader.eventlogger.db.Log)
	 */
	public void update(Log t) {
		t.setCustom3(Long.toString(new java.util.Date().getTime()));
		logDAO.update(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#delete(com.blackthorne.trader.eventlogger.db.Log)
	 */
	public void delete(Log t) {
		logDAO.delete(t);

	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#getAdminReviews()
	 */
	@Transactional(readOnly = true)
	public List<Log> getAdminReviews() {
		return logDAO.getAdminReviews();
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.LogService#get(java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	public Log get(Integer id) {
		return logDAO.get(id);
	}

	/**
	 * 
	 * @see com.blackthorne.trader.eventlogger.service.LogService#getRowsByGroups(java.util.List)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Log> getRowsByGroups(Collection<String> groups) {
		return logDAO.getRowsByGroups(groups);
	}

}
