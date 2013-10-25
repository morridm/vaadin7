package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.spring;


import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.ui.SimpleLoginView;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.ViewChangeListener;

@SuppressWarnings("serial")
public class ViewChangeSecurityChecker implements ViewChangeListener {

	public boolean beforeViewChange(ViewChangeEvent event) {

		if (event.getNewView() instanceof SimpleLoginView) {

			return true;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null){
			return false;
		}
		
		return authentication.isAuthenticated();
		
	}
	
	@Override
	public void afterViewChange(ViewChangeEvent event) {
		
	}
}
