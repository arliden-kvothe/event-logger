package com.blackthorne.trader.eventlogger.dao;

import java.util.Collection;
import java.util.List;

import com.blackthorne.trader.eventlogger.db.Log;

/**
 * Manage CRUD methods over Log table
 * @author Alejandro Hern&#225;ndez P&#233;rez
 * 
 */
public interface LogDAO extends DAO<Log> {
	
	List<Log> getAdminReviews();
	
	List<Log> getRowsByGroups(Collection<String> groups);
	
}