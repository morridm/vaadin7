package nl.kaninefatendreef.tutorial.vaadin7.vaadin7XpoftXpringSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    public void configure(WebSecurity webSecurity) throws Exception {
	
		webSecurity.ignoring()
        .antMatchers("/VAADIN/**")
        .antMatchers("/**/*.css")
        .antMatchers("/**/*.js")
        .antMatchers("/#!login"); 	
		  
	}
	
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http
	    .authorizeUrls()
	      .antMatchers("/#!login").hasRole("IS_AUTHENTICATED_ANONYMOUSLY")
	      .antMatchers("/#!main").hasRole("USER")
//	      .anyRequest().authenticated()
	      .and()
	  .formLogin() 
	      .loginUrl("/#!login") 
	      .permitAll();
		
		
		
		//  <intercept-url pattern="/login/" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
	    //    <intercept-url pattern="/**" access="ROLE_USER, ROLE_ADMIN" />
	    //    <form-login login-page="/login/" authentication-failure-url="/login/?error=true"/>
		
       
    }
	
	 @Override
     protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 auth
              .inMemoryAuthentication()
                   .withUser("user")
                        .password("password")
                        .roles("USER").and().withUser("admin")
                        .password("password")
                        .roles("ADMIN");
              ;
     }

	@Bean 
	@Override 
	public AuthenticationManager authenticationManagerBean() 
	            throws Exception { 
	        return super.authenticationManagerBean(); 
	} 
	 
	 @Bean
	 public SecurityContextLogoutHandler securityContextLogoutHandler() 
	            throws Exception { 
		
		 SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		 securityContextLogoutHandler.setInvalidateHttpSession(false);
		 return securityContextLogoutHandler; 
	} 
	 
	
	 
}