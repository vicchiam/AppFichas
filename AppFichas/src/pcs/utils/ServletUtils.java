package pcs.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	public static void showError(Servlet servlet, HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
		request.setAttribute("Error", message);
		ServletUtils.setResponseController(servlet, Params.JSP_PATH+"error").forward(request, response);		
	}
	
	public static void showErrorAjax(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
		response.getWriter().print(message);
	}
	
	
}
