package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld;

import javax.servlet.annotation.WebServlet;

import ru.xpoft.vaadin.SpringVaadinServlet;

import com.vaadin.annotations.VaadinServletConfiguration;


@SuppressWarnings("serial")
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = Vaadin7SprinItegrationHelloworldUI.class, widgetset = "nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld.widgetset.Vaadin7_spring_integration_helloworldWidgetset")
public class Vaadin7SprinItegrationHelloworldServlet extends SpringVaadinServlet {

}