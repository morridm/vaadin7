package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringIntegrationHelloworld;

import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldBean {

	public String getDateTime(){
		
		GregorianCalendar cal = new GregorianCalendar();
			
		String y = "" + cal.get(GregorianCalendar.YEAR);
		String m = "" + (cal.get(GregorianCalendar.MONTH) + 1);
		String d = "" + cal.get(GregorianCalendar.DAY_OF_MONTH);
		String h = "" + cal.get(GregorianCalendar.HOUR_OF_DAY);
		String min = "" + cal.get(GregorianCalendar.MINUTE);
		String sec = "" + cal.get(GregorianCalendar.SECOND);
		
		return y + "-"+ m + "-" + d + " " + h + ":" + min + ":" + sec;
		
		
	}
	
}
