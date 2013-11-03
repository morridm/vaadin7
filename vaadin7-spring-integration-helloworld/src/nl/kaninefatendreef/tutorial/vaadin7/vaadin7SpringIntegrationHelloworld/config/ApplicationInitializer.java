package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		
	     AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
	     annotationConfigWebApplicationContext.scan("nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld");         
	     servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));                
	     
         /*
          * Spring components mapping
          */
         Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
		
	}
	
	
	
	
}