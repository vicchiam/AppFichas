package pcs.pack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcs.users.User;
import pcs.utils.Params;
import pcs.utils.ServletUtils;
import pcs.utils.Window;

/**
 * Servlet implementation class PackController
 */
public class PackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackController() {
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
			ServletUtils.setResponseController(this,Params.JSP_PATH+"login").forward(request, response);
		}
		
		String action=request.getParameter("action");	
		
		if(action.equals("list")){
			this.showListusers(request, response);
		}
	}
	
	private void showListusers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("PACK",400,300,"Envases"));		
		request.setAttribute("windows", windows);
		
		String description=request.getParameter("f_description");
		request.setAttribute("f_description", description);
		
		String apt=request.getParameter("f_apt");
		if(apt==null) apt="1";
		request.setAttribute("f_apt", apt);		
		
		String state=request.getParameter("state");
		if(state==null) state="1";
		request.setAttribute("f_state", state);	
		
		
		try{
			PackBusiness packBusiness=new PackBusiness();
			Collection<Pack> listPacks=packBusiness.listPacks(description,Integer.parseInt(apt), Integer.parseInt(state));
			request.setAttribute("listPacks", listPacks);			
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/pack").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/listUsers").forward(request, response);
			}			
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			this.showError(request, response, e.getMessage());
		}		
	}
	
	private void showError(HttpServletRequest request, HttpServletResponse response, String message, boolean isAjax) throws ServletException, IOException{
		if(isAjax){
			response.getWriter().print(message);
		}
		else{
			request.setAttribute("Error", message);
			ServletUtils.setResponseController(this, Params.JSP_PATH+"error").forward(request, response);
		}
	}
	
	private void showError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
		this.showError(request, response, message, false);
	}
	

}
