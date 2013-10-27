package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.event;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginEvent implements Serializable{

	private final String _login;

	private final String _password;

	public LoginEvent(String login, String password) {
		_login = login;
		_password = password;
	}

	public String getLogin() {
		return _login;
	}

	public String getPassword() {
		return _password;
	}
}
