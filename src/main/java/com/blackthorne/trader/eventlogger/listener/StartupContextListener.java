package com.blackthorne.trader.eventlogger.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.blackthorne.trader.eventlogger.util.ScheduledTestDBConnection;

/**
 * Application Lifecycle Listener implementation class StartupContextListener
 *
 *	@author Daniel Hernandez
 */
public class StartupContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartupContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	Logger logger = null;
	    String log4jFile = sce.getServletContext().getInitParameter("log4jConfig");
	    DOMConfigurator.configure(sce.getServletContext().getRealPath(log4jFile));
	    logger = LogManager.getLogger(StartupContextListener.class.getName());
	    logger.debug("Loaded: " + log4jFile);
	    
	    // Scheduled TestQuery
	    ScheduledTestDBConnection.testConnection();
//	    ScheduledTestDBConnection scheduledTestDBConnection = ScheduledTestDBConnection.getInstance(); 
//	    scheduledTestDBConnection.testConnection();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        ScheduledTestDBConnection.stopTestingConnection();
    }
	
}
