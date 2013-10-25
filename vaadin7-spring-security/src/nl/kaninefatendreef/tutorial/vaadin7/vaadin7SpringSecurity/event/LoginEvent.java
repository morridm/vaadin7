package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.event;

import java.io.Serializable;



@SuppressWarnings("serial")
public class LoginEvent implements Serializable{

	private final String login;

	private final String password;

	public LoginEvent(String login, String password) {

		this.login = login;
		this.password = password;
	}

	public String getLogin() {

		return login;
	}

	public String getPassword() {

		return password;
	}
}
