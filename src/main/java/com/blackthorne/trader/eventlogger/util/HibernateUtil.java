package com.blackthorne.trader.eventlogger.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utils to handle connection to Database 
 * @author Alejandro Hern&#225;ndez P&#233;rez
 *
 */

public class HibernateUtil {

	private static SessionFactory sf;

	/**
	 * Getting {@link SessionFactory} and validates if it is null or closed<br>
	 * file to get configuration of sessionFactory is <code>resources/hibernate.cfg.xml</code>
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		if ((sf == null) || (sf.isClosed())) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure("resources/hibernate.cfg.xml");
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
				sf = configuration.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		return sf;
	}
}