package com.blackthorne.trader.eventlogger.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.blackthorne.trader.eventlogger.db.Log;
import com.blackthorne.trader.eventlogger.service.LogService;

/**
 * @author Eduardo Barcenas
 * 
 */
@ManagedBean(name = "logBean")
@RequestScoped
public class LogBean implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 6116555653291868940L;

	private static final Logger logger = Logger.getLogger(LogBean.class);

	// Spring Services are injected...
	@ManagedProperty(value = "#{logService}")
	private LogService logService;

	@ManagedProperty("#{param.eventId}")
	private Integer eventId;
	@ManagedProperty("#{param.systemId}")
	private Integer systemId;
	@ManagedProperty("#{param.comments}")
	private String comments;
	@ManagedProperty("#{param.custom1}")
	private String custom1;

	public void save() {
		Log log = new Log();
		log.setEventId(eventId);
		log.setSystemId(systemId);
		log.setCustom1(custom1);
		log.setComments(comments);
		logger.debug("log to save = ["
				+ ToStringBuilder.reflectionToString(log,
						ToStringStyle.MULTI_LINE_STYLE) + "]");
		Integer id = logService.save(log);

		logger.debug("id log saved = [" + id + "]");
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the systemId
	 */
	public Integer getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId
	 *            the systemId to set
	 */
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the custom1
	 */
	public String getCustom1() {
		return custom1;
	}

	/**
	 * @param custom1
	 *            the custom1 to set
	 */
	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	// ----------------- Spring 
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
