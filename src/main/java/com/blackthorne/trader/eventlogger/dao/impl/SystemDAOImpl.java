package com.blackthorne.trader.eventlogger.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackthorne.trader.eventlogger.dao.SystemDAO;
import com.blackthorne.trader.eventlogger.db.System;

/**
 * @author Eduardo Barcenas
 *
 */
@Repository("systemDAO")
public class SystemDAOImpl implements SystemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#save(java.lang.Object)
	 */
	public Integer save(System t) {
		return (Integer) sessionFactory.getCurrentSession().save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#getRows()
	 */
	@SuppressWarnings("unchecked")
	public List<System> getRows() {
		
		Query q = sessionFactory.getCurrentSession().getNamedQuery("system.rows");		
		List<System> result = q.list();
		return result;
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#update(java.lang.Object)
	 */
	public void update(System t) {
		sessionFactory.getCurrentSession().update(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#delete(java.lang.Object)
	 */
	public void delete(System t) {
		sessionFactory.getCurrentSession().delete(t);

	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#get(java.lang.Integer)
	 */
	public System get(Integer id) {
		return (System) sessionFactory.getCurrentSession().get(System.class, id);
	}

}
