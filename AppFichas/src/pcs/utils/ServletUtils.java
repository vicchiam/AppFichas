package pcs.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	
	public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
	
}
