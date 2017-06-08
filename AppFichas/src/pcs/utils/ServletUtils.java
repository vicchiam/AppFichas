package pcs.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;


public class ServletUtils {

	public static RequestDispatcher setResponseController(Servlet servlet, String view){
		String pathJSP=InitParams.getPathJSP();
		String url=pathJSP+"/"+view+".jsp";
		return servlet.getServletConfig().getServletContext().getRequestDispatcher(url);
	}
	
}
