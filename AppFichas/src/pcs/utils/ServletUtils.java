package pcs.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.http.HttpSession;

import pcs.users.User;


public class ServletUtils {

	public static RequestDispatcher setResponseController(Servlet servlet, String view){		
		String url=view+".jsp";
		return servlet.getServletConfig().getServletContext().getRequestDispatcher(url);
	}
	
	public static boolean authorized(HttpSession session){
		if(session.getAttribute("user")==null){
			return false;
		}
		if(!(session.getAttribute("user") instanceof User)){
			return false;
		}
		if(((User)session.getAttribute("user")).getId()==0){
			return false;
		}
		return true;
	}
	
}
