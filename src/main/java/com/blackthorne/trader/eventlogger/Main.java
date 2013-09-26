package com.blackthorne.trader.eventlogger;

import java.util.List;

import org.hibernate.Transaction;

import com.blackthorne.trader.eventlogger.db.Event;
import com.blackthorne.trader.eventlogger.db.Log;
import com.blackthorne.trader.eventlogger.db.System;
import com.blackthorne.trader.eventlogger.util.HibernateUtil;

/**
 * @deprecated this class crashes with the strategy of developing by tiers
 *             according to the MVC design pattern.
 */
@Deprecated
public class Main {

	/**
	 * Gets Transaction from currentSession in sessionFactory used in
	 * HibernateUtil class
	 */
	public Transaction getTransaction() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.getTransaction();
	}

	/**
	 * Begins an existing transaction. If the transaction is null it begins a new one
	 */
	public static void beginTransaction() {
		//HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()==null)
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		else
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().begin();
	}

	/**
	 * To factor Transaction's operations.
	 */
	public static void closeTransaction() {
		// To 'release' transactions created by Hibernate
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		// Closing SessionFactory
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/**
	 * Storing Event in Database
	 */
	public static Integer save(Event row) {
		beginTransaction();
		//Integer id = EventDAO.save(row);
		closeTransaction();

		return null;
	}

	/**
	 * Storing Log in Database
	 */
	public static Integer save(Log row) {
		beginTransaction();
		//Integer id = LogDAO.save(row);
		closeTransaction();

		return null;
	}

	/**
	 * Storing System in Database
	 */
	public static Integer save(System row) {
		beginTransaction();
		//Integer id = SystemDAO.save(row);
		closeTransaction();

		return null;
	}
	
	/**
	 * Retrieving Event from Database
	 */
	public static List<Event> getEvents() {
		beginTransaction();
		//List<Event> rows = EventDAO.getRows();
		closeTransaction();

		return null;
	}

	/**
	 * Retrieving Log from Database
	 */
	public static List<Log> getLogs() {
		beginTransaction();
		//List<Log> rows = LogDAO.getRows();
		closeTransaction();

		return null;
	}

	/**
	 * Retrieving Systems from Database
	 */
	public static List<System> getSystems() {
		beginTransaction();
		//List<System> rows = SystemDAO.getRows();
		closeTransaction();

		return null;
	}

	/**
	 * Retrieving Event from Database
	 */
	public static Event getEvent(int id) {
		beginTransaction();
		//Event row = EventDAO.get(id);
		closeTransaction();

		return null;
	}

}
