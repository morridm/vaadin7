package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		
	     AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
	     annotationConfigWebApplicationContext.scan("nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity");
         
	     /*
          * Spring security filter mapping
          */
         servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));                
         FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
         filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
         
         /*
          * Spring components mapping
          */
         Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
		
	}
	
	
	
	
}