package com.blackthorne.trader.eventlogger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackthorne.trader.eventlogger.dao.SystemDAO;
import com.blackthorne.trader.eventlogger.db.System;
import com.blackthorne.trader.eventlogger.service.SystemService;

/**
 * @author Eduardo Barcenas
 *
 */
@Service("systemService")
@Transactional(readOnly = false)
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	private SystemDAO systemDAO;
	
	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemService#save(com.blackthorne.trader.eventlogger.db.System)
	 */
	public Integer save(System t) {
		return systemDAO.save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemService#getRows()
	 */
	@Transactional(readOnly = true)
	public List<System> getRows() {
		return systemDAO.getRows();
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemService#update(com.blackthorne.trader.eventlogger.db.System)
	 */
	public void update(System t) {
		systemDAO.update(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemService#delete(com.blackthorne.trader.eventlogger.db.System)
	 */
	public void delete(System t) {
		systemDAO.delete(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemService#get(java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	public System get(Integer id) {
		return systemDAO.get(id);
	}

}
