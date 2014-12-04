package com.blackthorne.trader.eventlogger.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blackthorne.trader.eventlogger.dao.SystemGroupDAO;

/**
 * @author Eduardo Barcenas
 *
 */
@Repository("systemGroupDAO")
@Transactional(readOnly = true)
public class SystemGroupDAOImpl implements SystemGroupDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.SystemGroupDAO#getGroups()
	 */
	@SuppressWarnings("unchecked")
	public List<String> getGroups() {
		Query q = sessionFactory.getCurrentSession().getNamedQuery("systemGroup.groups");		
		List<String> result = q.list();
		return result;
	}

}
