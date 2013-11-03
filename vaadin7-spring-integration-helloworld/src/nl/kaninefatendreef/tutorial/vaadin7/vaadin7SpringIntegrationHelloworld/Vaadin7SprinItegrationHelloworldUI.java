package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadin7_spring_integration_helloworld")
@Component("ui") // For the VaadinServlet name must be ui
@Scope("prototype")
public class Vaadin7SprinItegrationHelloworldUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
			
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
		navigator.navigateTo("main");
	
	}

}