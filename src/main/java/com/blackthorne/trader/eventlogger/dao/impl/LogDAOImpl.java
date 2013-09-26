package com.blackthorne.trader.eventlogger.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackthorne.trader.eventlogger.dao.LogDAO;
import com.blackthorne.trader.eventlogger.db.Log;

/**
 * @author Eduardo Barcenas
 *
 */
@Repository("logDAO")
public class LogDAOImpl implements LogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#save(java.lang.Object)
	 */
	public Integer save(Log t) {
		return (Integer) sessionFactory.getCurrentSession().save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#getRows()
	 */
	@SuppressWarnings("unchecked")
	public List<Log> getRows() {
		
		Query q = sessionFactory.getCurrentSession().getNamedQuery("log.rows");		
		List<Log> result = q.list();
		return result;
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#update(java.lang.Object)
	 */
	public void update(Log t) {
		sessionFactory.getCurrentSession().update(t);

	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#delete(java.lang.Object)
	 */
	public void delete(Log t) {
		sessionFactory.getCurrentSession().delete(t);

	}

	@SuppressWarnings("unchecked")
	public List<Log> getAdminReviews() {
		
		Query q = sessionFactory.getCurrentSession().getNamedQuery("log.rows.admin");
		List<Log> result = q.list();
		return result;
	}

	public Log get(Integer id) {
		return (Log) sessionFactory.getCurrentSession().get(Log.class, id);
	}

}
