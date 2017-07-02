package pcs.language;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcs.utils.Params;
import pcs.utils.ServletUtils;
import pcs.utils.Window;

/**
 * Servlet implementation class LanguageController
 */
public class LanguageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		if(!ServletUtils.authorized(session)){
			ServletUtils.setResponseController(this, "/jsp/login").forward(request, response);
		}
		
		String action=request.getParameter("action");	
		
		if(action.equals("list")){
			this.listLanguages(request, response);
		}
		
	}
	
	private void listLanguages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("LAN",300,120,"Lenguaje"));			
		request.setAttribute("windows", windows);		
		
		String name=request.getParameter("f_name");
		request.setAttribute("f_name", name);
		
		String state=request.getParameter("state");
		if(state==null) state=Params.ACTIVE+"";
		request.setAttribute("f_state", state);
		
		try {
			Collection<Language> listLanguages = new LanguageBusiness().listLanguages(name, Integer.parseInt(state));
			request.setAttribute("listLanguages", listLanguages);
			
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"languages/language").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"languages/listLanguages").forward(request, response);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			if(request.getParameter("ajax")==null){
				ServletUtils.showError(this, request, response, e.getMessage());
			}
			else{
				ServletUtils.showErrorAjax(request, response, e.getMessage());
			}
		}
		
		
		
		
		
	}

}
