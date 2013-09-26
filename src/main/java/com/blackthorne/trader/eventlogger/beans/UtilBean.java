package com.blackthorne.trader.eventlogger.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.TransactionException;
import org.primefaces.context.RequestContext;

import com.blackthorne.trader.eventlogger.db.Log;
import com.blackthorne.trader.eventlogger.db.System;
import com.blackthorne.trader.eventlogger.service.LogService;
import com.blackthorne.trader.eventlogger.service.SystemService;
import com.blackthorne.trader.eventlogger.util.HibernateUtil;

/**
 * Handling interaction between UI and Model layer
 * 
 * @author Alejandro Hern&#225;ndez P&#233;rez
 * 
 */
@ManagedBean(name = "utilBean")
@RequestScoped
public class UtilBean implements Serializable {
	private static final long serialVersionUID = -4669410611054037778L;
	
	private static final Logger log = Logger.getLogger(UtilBean.class);

	private List<Log> logs;
	private List<Log> adminReviews;
	private List<System> systems;
	private int systemId;
	private int fileSize;
	private String comments;
	
	//Spring Services are injected...
    @ManagedProperty(value="#{logService}")
	private LogService logService;
    @ManagedProperty(value="#{systemService}")
	private SystemService systemService;

	/**
	 * Method to factor out to start one transaction
	 * 
	 * @deprecated This is not thread safety and may implicate some persistence
	 *             issues.<br>
	 *             Hibernate session is now manage by only one context through
	 *             the web server by SpringFramework's help.<br>
	 *             This method needs to be removed.
	 * 
	 */
	@Deprecated
	private void beginTransaction() {
		try {
			// Starting Transaction
			//HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()==null)
				HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			else
				HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().begin();
		} catch (TransactionException e) {
			log.error("********** An error occurred in the transaction **********", e);
			log.info("Retrying....");
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			HibernateUtil.getSessionFactory().getCurrentSession().createSQLQuery("select 1").list();
		}

	}

	/**
	 * Method to factor out to close one transaction
	 * 
	 * @deprecated This is not thread safety and may implicate some persistence
	 *             issues.<br>
	 *             Hibernate session is now manage by only one context through
	 *             the web server by SpringFramework's help.<br>
	 *             This method needs to be removed.
	 * 
	 */
	@Deprecated
	private void closeTransaction() {
		// To 'release' transactions created by Hibernate
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		// Closing SessionFactory
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/* ******************
	 * GETTERS & SETTERS
	 */

	/**
	 * Setting logs 
	 * @param logs List<{@link Log}>
	 */
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	/**
	 * Setting adminReviews 
	 * @param logs List<{@link Log}>
	 */
	public void setReviews(List<Log> adminReviews) {
		this.adminReviews = adminReviews;
	}

	/**
	 * Setting systems
	 * @param systems List<{@link System}>
	 */
	public void setSystems(List<System> systems) {
		this.systems = systems;
	}

	/**
	 * Method to retrieve all {@link Log}s from dataBase
	 */
	public List<Log> getLogs() {
		log.debug("logService = " + logService);
		logs = logService.getRows();
		return logs;
	}

	/**
	 * Method to retrieve all {@link Log}s to review
	 */
	public List<Log> getAdminReviews() {
		adminReviews = logService.getAdminReviews();
		return adminReviews;
	}

	/**
	 * Method to retrieve all {@link System}s from dataBase
	 */
	public List<System> getSystems() {
		systems = systemService.getRows();
		return systems;
	}

	/**
	 * Gets comments
	 * @return String
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Setting comments
	 * @param comments {@link String}
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets fileSize
	 * @return int
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * Setting fileSize
	 * @param fileSize int
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets systemId
	 * @return int
	 */
	public int getSystemId() {
		return systemId;
	}

	/**
	 * Setting systemId
	 * @param systemId int
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * Method used when the user has reviewed all {@link Log}s
	 */
	public void save() {
		/*
		 * TODO - Modify insertion (eventId) by properties
		 */

		// To get bean of Logged user
		FacesContext context = null;
		context = FacesContext.getCurrentInstance();
				
		UserBean userBean = (UserBean) context
				.getApplication()
				.evaluateExpressionGet(context, "#{personBean}", UserBean.class);

		Log userLog = new Log();
		userLog.setEventId(1);
		userLog.setSystemId(Integer.valueOf(systemId));
		userLog.setDateTime(new java.sql.Timestamp(java.lang.System
				.currentTimeMillis()));
		userLog.setComments(comments);
		// Size of file entered by user
		userLog.setFileSize(fileSize);
		// User logged
		userLog.setCustom1(userBean.getUsername());

		// Storing log
		logService.save(userLog);

		// Clear inputs
		RequestContext.getCurrentInstance().reset("form:panel");
		this.fileSize = 0;
		this.comments = null;

		// Redirects to same page
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath()
					+ "/user.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", e
							.getMessage()));
		}

	}

}
