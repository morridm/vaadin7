package nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.view;


import nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.event.LogoutEvent;

import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class SimpleLoginMainView extends CustomComponent implements View {

    public static final String NAME = "main";

    EventBus _eventBus = null; 
   
    Label _text = new Label();

    Button _logout = new Button("Logout", new Button.ClickListener() {
    	
    	@Override
		public void buttonClick(ClickEvent event) {
    		_eventBus.post(new LogoutEvent());
		}
    	
     });

    public SimpleLoginMainView(EventBus eventBus) {
    	_eventBus = eventBus;
        setCompositionRoot(new CssLayout(_text, _logout));
    }

   

	@Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session      
		if (SecurityContextHolder.getContext().getAuthentication() == null){
			// navigate to login
			
			return;
		}
		
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        // And show the username
        _text.setValue("Hello " + user);
    }
}