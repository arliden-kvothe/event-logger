package com.blackthorne.trader.eventlogger.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;

/**
 * Handling User Bean
 * 
 * @author Alejandro Hern&#225;ndez P&#233;rez
 * 
 */
@ManagedBean(name = "personBean")
@SessionScoped 
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488190956816537132L;
	
	private static final Logger log = Logger.getLogger(UserBean.class);
	
	private String username;
	private String password;
	private boolean isUserLogged = false;
	private boolean isAdminLogged = false;
	private boolean isReportLogged = false;

	/**
	 * Gets UserName
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setting UserName
	 * @param username String
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets Password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setting Password
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Knows if operator is logged
	 * @return boolean
	 */
	public boolean isUserLogged() {
		return isUserLogged;
	}

	/**
	 * Setting if operator is logged
	 * @param isUserLogged boolean
	 */
	public void setUserLogged(boolean isUserLogged) {
		this.isUserLogged = isUserLogged;
	}

	/**
	 * Knows if administrator is logged
	 * @return String
	 */
	public boolean isAdminLogged() {
		return isAdminLogged;
	}

	/**
	 * Setting if administrator is logged
	 * @param isAdminLogged boolean
	 */
	public void setAdminLogged(boolean isAdminLogged) {
		this.isAdminLogged = isAdminLogged;
	}
	
	public boolean isReportLogged() {
		return this.isReportLogged;
	}
	
	public void setReportLogged(boolean isReportLogged) {
		this.isReportLogged = isReportLogged;
	}

	/**
	 * Method to check User/Password then <br>
	 * redirects to right page or raises an error. 
	 * 
	 */
	public void validateUser(ActionEvent actionEvent) throws IOException {
		isUserLogged = false;
		isAdminLogged = false;
		
		log.debug("Log in attemp as " + username );

		if (username.equals("operator")) {
			if (password.equals("userBt02")) {
				ExternalContext ec = FacesContext.getCurrentInstance()
						.getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/user.xhtml");
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Welcome "
				// + username + "!"));
				isUserLogged = true;
				isAdminLogged = false;
				return;
			}
		}

		if (username.equals("administrator")) {
			if (password.equals("Admin_00")) {
				ExternalContext ec = FacesContext.getCurrentInstance()
						.getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/admin.xhtml");
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Welcome "
				// + username + "!"));
				isUserLogged = false;
				isAdminLogged = true;
				return;
			}
		}

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
						"Username or password incorrect!"));
	}
	
	/**
	 * Redirects to index in case Operator is not logged
	 */
	public void verifyUserLogin(ComponentSystemEvent event) {  
		if(!isUserLogged){  
			doRedirect("index.xhtml");  
		}  
	 }

	/**
	 * Redirects to index in case Administrator is not logged
	 * @param event
	 */
	public void verifyAdminLogin(ComponentSystemEvent event) {  
		if(!isAdminLogged){  
			doRedirect("index.xhtml");  
		}  
	 }  
	
	/**
	 * Method to redirect to specific URL
	 * @param url String
	 */
	private void doRedirect(String url) {  
		try {  
			FacesContext context=FacesContext.getCurrentInstance();  
			context.getExternalContext().redirect(url);  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  
}
