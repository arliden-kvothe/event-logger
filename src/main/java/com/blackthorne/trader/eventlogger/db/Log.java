package com.blackthorne.trader.eventlogger.db;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO of Log 
 * @author Alejandro Hern&#225;ndez P&#233;rez
 *
 */

public class Log implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1543573665287230428L;
	/* *******************************
	 * 
	 * Properties of this Table
	 * 
	 * *******************************/	
	private Integer id;
	private Integer systemId;
	private Integer eventId;
	private Event event;
	private System system;
	private Timestamp dateTime;
	private Integer fileSize;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;

	/* *******************************
	 * 
	 * Getters & Setters of Properties
	 * 
	 * *******************************/
	/**
	 * Gets object's Id 
	 * @return {@link Integer} 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setting object's Id
	 * @param id {@link Integer}
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets object's systemId 
	 * @return {@link Integer} 
	 */
	public Integer getSystemId() {
		return systemId;
	}

	/**
	 * Setting object's systemId
	 * @param id {@link Integer}
	 */
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	/**
	 * Gets object's system 
	 * @return {@link System} 
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * Setting object's system
	 * @param system {@link System}
	 */
	public void setSystem(System system) {
		this.system = system;
	}

	/**
	 * Gets object's eventId 
	 * @return {@link Integer} 
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * Setting object's eventId
	 * @param eventId {@link Integer}
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets object's event 
	 * @return {@link Event} 
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Setting object's event
	 * @param event {@link Event}
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Gets object's dateTime 
	 * @return {@link Timestamp} 
	 */
	public Timestamp getDateTime() {
		return dateTime;
	}

	/**
	 * Setting object's dateTime
	 * @param dateTime {@link Timestamp}
	 */
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	
	/**
	 * Gets object's fileSize 
	 * @return {@link Integer} 
	 */
	public Integer getFileSize() {
		return fileSize;
	}

	/**
	 * Setting object's fileSize
	 * @param fileSize {@link Integer}
	 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets object's comments
	 * @return {@link String} 
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Setting object's comments
	 * @param comments {@link String}
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets object's custom1 
	 * @return {@link String} 
	 */
	public String getCustom1() {
		return custom1;
	}

	/**
	 * Setting object's custom1
	 * @param custom1 {@link String}
	 */
	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	/**
	 * Gets object's custom2
	 * @return {@link String} 
	 */
	public String getCustom2() {
		return custom2;
	}

	/**
	 * Setting object's custom2
	 * @param custom2 {@link String}
	 */
	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	/**
	 * Gets object's custom3
	 * @return {@link String} 
	 */
	public String getCustom3() {
		return custom3;
	}

	/**
	 * Setting object's custom3
	 * @param custom3{@link String}
	 */
	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Override
	public String toString() {
		return "Logs [id=" + id + ", systemId=" + systemId + ", eventId="
				+ eventId + ", dateTime=" + dateTime + ", comments=" + comments
				+ ", custom1=" + custom1 + ", custom2=" + custom2
				+ ", custom3=" + custom3 + "]";
	}

}
