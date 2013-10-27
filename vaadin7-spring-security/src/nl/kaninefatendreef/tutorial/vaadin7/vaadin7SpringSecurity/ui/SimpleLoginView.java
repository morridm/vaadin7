package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.ui;

import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.event.LoginEvent;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.google.common.eventbus.EventBus;



@SuppressWarnings("serial")
public class SimpleLoginView extends CustomComponent 

implements View,
        Button.ClickListener {

    public static final String NAME = "login";

    private final TextField _user;

    private final PasswordField _password;

    private final Button _loginButton;
 
    private EventBus _eventBus ;
   
    
    
    public SimpleLoginView(EventBus bus) {
    	_eventBus = bus;
    	  setSizeFull();

          // Create the user input field
          _user = new TextField("User:");
          _user.setWidth("300px");
          _user.setRequired(true);
          _user.setInputPrompt("Your username (eg. joe@email.com)");
          _user.addValidator(new EmailValidator("Username must be an email address"));
          _user.setInvalidAllowed(true);

          // Create the password input field
          _password = new PasswordField("Password:");
          _password.setWidth("300px");
          _password.addValidator(new PasswordValidator());
          _password.setRequired(true);
          _password.setValue("");
          _password.setNullRepresentation("");

          // Create login button
          _loginButton = new Button("Login", this);

          // Add both to a panel
          VerticalLayout fields = new VerticalLayout(_user, _password, _loginButton);
          fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
          fields.setSpacing(true);
          fields.setMargin(new MarginInfo(true, true, true, false));
          fields.setSizeUndefined();

          // The view root layout
          VerticalLayout viewLayout = new VerticalLayout(fields);
          viewLayout.setSizeFull();
          viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
          viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
          setCompositionRoot(viewLayout);
    	
	}

	@Override
    public void enter(ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        _user.focus();
    }

    //
    // Validator for validating the passwords
    //
    private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value != null
                    && (value.length() < 8 || !value.matches(".*\\d.*"))) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {

    	LoginEvent loginEvent = new LoginEvent(_user.getValue(), _password.getValue());

		_eventBus.post(loginEvent);
		
		_user.setValue("");
		_password.setValue("");
    	
			
	   }
}