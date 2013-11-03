package nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.xpoft.vaadin.SpringVaadinServlet;

import com.vaadin.annotations.VaadinServletConfiguration;


@SuppressWarnings("serial")
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = Vaadin7XpoftSpringSecurityUI.class)

public class Vaadin7XpoftSpringSecurityServlet extends SpringVaadinServlet {

	
	// This override method is the place to catch the request and put it in a class which is accesible
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