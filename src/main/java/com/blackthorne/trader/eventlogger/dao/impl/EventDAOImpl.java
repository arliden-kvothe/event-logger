/**
 * 
 */
package com.blackthorne.trader.eventlogger.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackthorne.trader.eventlogger.dao.EventDAO;
import com.blackthorne.trader.eventlogger.db.Event;

/**
 * @author Eduardo Barcenas
 * 
 */
@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#save(java.lang.Object)
	 */
	public Integer save(Event t) {
		
		return (Integer) sessionFactory.getCurrentSession().save(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#getRows()
	 */
	@SuppressWarnings("unchecked")
	public List<Event> getRows() {
		
		Query q = sessionFactory.getCurrentSession().getNamedQuery("event.rows");
		List<Event> result = q.list();
		return result;
		
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#update(java.lang.Object)
	 */
	public void update(Event t) {
		sessionFactory.getCurrentSession().update(t);
	}

	/**
	 * @see com.blackthorne.trader.eventlogger.dao.DAO#delete(java.lang.Object)
	 */
	public void delete(Event t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public Event get(Integer id) {
		return (Event) sessionFactory.getCurrentSession().get(Event.class, id);
	}

}
