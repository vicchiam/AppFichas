package pcs.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import pcs.main.Window;
import pcs.test.Test;
import pcs.utils.ServletUtils;

/**
 * Servlet implementation class UsersServlet
 */
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger("UserController: ");
	
	private String pathJSP;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//pathJSP=config.getInitParameter("pathJSP");	
		
		BasicConfigurator.configure();
				
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
			request.setAttribute("typeNames", User.typeNames);
			request.setAttribute("stateNames", User.stateNames);
			
			Collection<Window> windows=new ArrayList<Window>();
			//windows.add(new Window("USR",400,350,"Usuario"));
			//windows.add(new Window("MARK",400,350,"Marca"));
			request.setAttribute("windows", windows);
			
			String userName=request.getParameter("f_user");
			String email=request.getParameter("f_mail");
			String type=request.getParameter("f_type");
			String state=request.getParameter("f_state");
			
			//log.info("User:"+userName+" Mail:"+email+" Type:"+type+" State:"+state);
			
			UserDAO userDAO=new UserDAOImpl();
			Collection<User> listUsers=userDAO.listUsers(userName, email, type, state);
			
			//Collection<User> listUsers=Test.getUsers();
			request.setAttribute("listUsers", listUsers);
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, "/jsp/users/user").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, "/jsp/users/listUsers").forward(request, response);
			}			
		}		
		else if(action.equals("startSession")){			
			
			
		}
		else{
			//ServletUtils.setResponseController(this, "index").forward(request, response);
		}
		
		
	}

}
