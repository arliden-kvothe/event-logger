package com.blackthorne.trader.eventlogger.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LoginErrorPhaseListener implements PhaseListener {
	
	private static final long serialVersionUID = -1216620620302322995L; 

	public void beforePhase(final PhaseEvent arg0) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Exception e = (Exception) externalContext.getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (e != null) {
			if (e instanceof BadCredentialsException) {
				externalContext.getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
			} else {
				facesContext.getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server error ocurred! Please notify to the system administrator.", null));
			}
		}
		
	}

	public void afterPhase(final PhaseEvent arg0) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
