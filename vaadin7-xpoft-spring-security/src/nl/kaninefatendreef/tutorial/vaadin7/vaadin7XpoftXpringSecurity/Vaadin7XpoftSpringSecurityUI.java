package nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity;


import nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.event.LoginEvent;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.event.LogoutEvent;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.view.SimpleLoginMainView;
import nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.view.SimpleLoginView;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vaadin7_xpoft_spring_security")
@Component("ui") // For the VaadinServlet name must be ui
@Scope("prototype")
public class Vaadin7XpoftSpringSecurityUI extends UI implements ErrorHandler{

	// Important for getting a handle to the login request
	private EventBus _eventBus = new EventBus();

	@Subscribe
	public void login(LoginEvent event) {

		AuthenticationService authHandler = new AuthenticationService();
		try {
			authHandler.handleAuthentication(event.getLogin(),
					event.getPassword(), RequestHolder.getRequest());
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
	protected void init(final VaadinRequest request) {
		VaadinSession.getCurrent().setErrorHandler(this);
		setSizeFull();

		new Navigator(this, this);

		//
		// The initial log view where the user can login to the application
		//
		getNavigator().addView(SimpleLoginView.NAME,
				new SimpleLoginView(_eventBus));

		//
		// Add the main view of the application
		//
		getNavigator().addView(SimpleLoginMainView.NAME,
				new SimpleLoginMainView(_eventBus));

		getNavigator().addViewChangeListener(new ViewChangeSecurityChecker());
		
		getNavigator().navigateTo(SimpleLoginView.NAME);

		// Register subscribe annotations
		_eventBus.register(this);

	}

	    /**
	     * Exception on action
	     */
	    @Override
	    public void error(com.vaadin.server.ErrorEvent event)
	    {
	        // connector event
	        if (event.getThrowable().getCause() instanceof AccessDeniedException)
	        {
	            AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable().getCause();
	            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);

	            // Cleanup view. Now Vaadin ignores errors and always shows the view.  :-(
	            // since beta10
	            setContent(null);
	            return;
	        }

	        // Error on page load. Now it doesn't work. User sees standard error page.
	        if (event.getThrowable() instanceof AccessDeniedException)
	        {
	            AccessDeniedException exception = (AccessDeniedException) event.getThrowable();

	            Label label = new Label(exception.getMessage());
	            label.setWidth(-1, Unit.PERCENTAGE);

	            Link goToMain = new Link("Go to main", new ExternalResource("/"));

	            VerticalLayout layout = new VerticalLayout();
	            layout.addComponent(label);
	            layout.addComponent(goToMain);
	            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	            layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

	            VerticalLayout mainLayout = new VerticalLayout();
	            mainLayout.setSizeFull();
	            mainLayout.addComponent(layout);
	            mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

	            setContent(mainLayout);
	            Notification.show(exception.getMessage(), Notification.Type.ERROR_MESSAGE);
	            return;
	        }

	        DefaultErrorHandler.doDefault(event);
	    }

}