package com.blackthorne.trader.eventlogger.db;

import java.io.Serializable;

/**
 * DTO of System 
 * @author Alejandro Hern&#225;ndez P&#233;rez
 *
 */

public class System implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6556517351398484716L;
	/* *******************************
	 * 
	 * Properties of this Table
	 * 
	 * *******************************/	
	private Integer id;
	private String description;
	private String custom1;

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
	 * Gets object's description
	 * @return {@link String} 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setting object's description
	 * @param description {@link String}
	 */
	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "System [id=" + id + ", description=" + description
				+ ", custom1=" + custom1 + "]";
	}

}
