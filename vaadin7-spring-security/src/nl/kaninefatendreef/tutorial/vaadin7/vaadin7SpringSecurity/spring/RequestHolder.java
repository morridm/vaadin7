package nl.kaninefatendreef.tutorial.vaadin7.vaadin7SpringSecurity.spring;



import javax.servlet.http.HttpServletRequest;

public class RequestHolder {

	private static final ThreadLocal<HttpServletRequest> THREAD_LOCAL = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		
		return THREAD_LOCAL.get();
	}
	
	public static void setRequest(HttpServletRequest request) {
		
		THREAD_LOCAL.set(request);
	}
	
	public static void clean() {
		
		THREAD_LOCAL.remove();
	}
}
