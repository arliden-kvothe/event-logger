/**
 * 
 */
package com.blackthorne.trader.eventlogger.dao;

import java.util.List;

/**
 * @author Eduardo Barcenas
 * 
 */
public interface DAO<T> {

	Integer save(T t);

	List<T> getRows();

	void update(T t);
	
	void delete (T t);
	
	T get(Integer id); 

}
