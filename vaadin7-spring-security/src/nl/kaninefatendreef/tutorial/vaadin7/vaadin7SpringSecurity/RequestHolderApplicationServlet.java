package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;


@SuppressWarnings("serial")

@WebServlet(value = "/*",
asyncSupported = true)
@VaadinServletConfiguration(
productionMode = false,
ui = Vaadin7SpringSecurityUI.class)

public class RequestHolderApplicationServlet extends VaadinServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());

		RequestHolder.setRequest(request);

		super.service(request, response);

		// We remove the request from the thread local, there's no reason to keep it once the work is done
		RequestHolder.clean();

		SecurityContextHolder.clearContext();
	}
}
