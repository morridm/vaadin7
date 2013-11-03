package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld.view;




import nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld.HelloWorldBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(SimpleMainView.NAME)
public class SimpleMainView extends CustomComponent implements View {

    public static final String NAME = "main";

    @Autowired
    HelloWorldBean _helloworldBean = null;
    
    Label _text = new Label();

    Button _click = new Button("Clicks", new Button.ClickListener() {
    	
    	@Override
		public void buttonClick(ClickEvent event) {
    		_text.setValue(_helloworldBean.getDateTime());
		}
    	
     });

    public SimpleMainView() {
        setCompositionRoot(new CssLayout(_text, _click));
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

   

}