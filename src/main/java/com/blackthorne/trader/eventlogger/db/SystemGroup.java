package com.blackthorne.trader.eventlogger.db;

import java.io.Serializable;

/**
 * @author Eduardo Barcenas
 *
 */
public class SystemGroup implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -5517141865171526370L;
	
	private Integer id;
	
	private String groupName;
	
	private System system;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the system
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(System system) {
		this.system = system;
	}

}
