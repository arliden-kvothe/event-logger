package com.blackthorne.trader.eventlogger.util;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ScheduledTestDBConnection {
	private static final long DELAY = 5 * 60;
	private static final long PERIOD = 30 * 60;
	
	
	private static final Logger log = Logger.getLogger(ScheduledTestDBConnection.class);
	
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static ScheduledFuture<?> testConnectionHandle;
	private static Thread schedulerThread = null;
	
	private static  boolean isRunning = false;
	

	public static void testConnection() {
		
		System.out.println("isRunning: " + isRunning);
		
		if (!isRunning) {
			
			isRunning = true;
		
			final Runnable testConnection = new Runnable() {
				
				public void run() {
					if (schedulerThread == null) {
						schedulerThread = Thread.currentThread();
					}
					
					log.debug("Executing Validation Query.... [" + new Date() + "]");
//					System.out.println("Executing Validation Query.... [" + new Date() + "]");
					
					try {
						if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()==null) {
							HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
						} else {
							HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().begin();
						}
						
						HibernateUtil.getSessionFactory().getCurrentSession().createSQLQuery("select 1").list();
						
						
					} catch(Exception e) {
						log.error("XXXXXXXXXXX Error while Testing the DB Connection", e);
					} finally {
						HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
					}
				}
			};
			
			testConnectionHandle = scheduler.scheduleAtFixedRate(testConnection, DELAY, PERIOD, TimeUnit.SECONDS);
			
		}

	}
	
	public static void stopTestingConnection() {
		boolean executionCanceled = testConnectionHandle.cancel(true);
		scheduler.shutdownNow();
		if (schedulerThread.isAlive() && !schedulerThread.isInterrupted()) {
			schedulerThread.interrupt();
		}
		scheduler = null;
	}
	
	public static void main(String[] args) {
		for (int i=0; i<5; i++) {
			ScheduledTestDBConnection.testConnection();
		}
	}
}
