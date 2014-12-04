package com.blackthorne.trader.eventlogger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackthorne.trader.eventlogger.dao.SystemGroupDAO;
import com.blackthorne.trader.eventlogger.service.SystemGroupService;

/**
 * @author Eduardo Barcenas
 *
 */
@Service("systemGroupService")
public class SystemGroupServiceImpl implements SystemGroupService {
	
	@Autowired
	private SystemGroupDAO systemGroupDAO;

	/**
	 * @see com.blackthorne.trader.eventlogger.service.SystemGroupService#getGroups()
	 */
	public List<String> getGroups() {
		return systemGroupDAO.getGroups();
	}

}
