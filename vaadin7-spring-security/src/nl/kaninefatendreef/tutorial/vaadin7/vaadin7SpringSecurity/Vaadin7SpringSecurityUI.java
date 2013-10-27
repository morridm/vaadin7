package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity;


import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.event.LoginEvent;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.event.LogoutEvent;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.spring.AuthenticationService;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.spring.RequestHolder;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.spring.ViewChangeSecurityChecker;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.ui.SimpleLoginMainView;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.ui.SimpleLoginView;

import org.springframework.security.authentication.BadCredentialsException;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadin7springsecurity")
public class Vaadin7SpringSecurityUI extends UI {

	private EventBus _eventBus = new EventBus();
	
	// Subscribe annotations are processed by the event bus when called register this
	
	@Subscribe
	public void login(LoginEvent event) {

		AuthenticationService authHandler = new AuthenticationService();

		try {
			authHandler.handleAuthentication(event.getLogin(), event.getPassword(), RequestHolder.getRequest());
			getNavigator().navigateTo(SimpleLoginMainView.NAME);
		} catch (BadCredentialsException e) {
			Notification.show("Bad credentials", Type.ERROR_MESSAGE);
		}
	}

	@Subscribe
	public void logout(LogoutEvent event) {

		AuthenticationService authHandler = new AuthenticationService();
		
		authHandler.handleLogout(RequestHolder.getRequest());

		 getNavigator().navigateTo(SimpleLoginView.NAME);
	}

	@Override
	protected void init(VaadinRequest request) {
		
		
		// Object to switch between views
		new Navigator(this, this);
	    
		//
	    // The initial log view where the user can login to the application
	    //
	    getNavigator().addView(SimpleLoginView.NAME, new SimpleLoginView(_eventBus));

	    //
	    // Add the main view of the application
	    //
	    getNavigator().addView(SimpleLoginMainView.NAME,new SimpleLoginMainView(_eventBus));
			        
	    getNavigator().addViewChangeListener(new ViewChangeSecurityChecker());
	
	    // Start view
		getNavigator().navigateTo(SimpleLoginView.NAME);
  
		// Register subscribe annotations
		_eventBus.register(this);

	}

}