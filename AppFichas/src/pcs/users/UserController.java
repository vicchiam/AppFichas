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
		
		request.setAttribute("typeNames", User.typeNames);
		request.setAttribute("stateNames", User.stateNames);
		
		if(action.equals("list")){
			this.showListUsers(request, response);	
		}		
		else if(action.equals("showNewUser")){			
			this.showNewUser(request, response);			
		}
		else if(action.equals("showUpdateUser")){
			this.showUpdateUser(request, response);
		}
		else if(action.equals("saveUser")){
			this.saveUser(request, response);	
		}
		else if(action.equals("changeStateUser")){
			this.changeStateUser(request, response);	
		}
		else if(action.equals("showChangePassword")){
			this.showChangePassword(request, response);	
		}
		else{
			ServletUtils.setResponseController(this, "/jsp/index").forward(request, response);
		}	
		
	}
	
	private void showListUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("USR",400,160,"Usuario"));			
		request.setAttribute("windows", windows);
		
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String email=request.getParameter("f_mail");
		request.setAttribute("f_mail", email);
		String type=request.getParameter("f_type");
		request.setAttribute("f_type", type);
		String state=request.getParameter("f_state");
		request.setAttribute("f_state", state);
		
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
	
	private void showNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("user",new User());		
		ServletUtils.setResponseController(this, "/jsp/users/formUser").forward(request, response);
	}
	
	private void showUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		UserDAO userDAO=new UserDAOImpl();
		User user=userDAO.getUser(id);
		request.setAttribute("user",user);
		
		ServletUtils.setResponseController(this, "/jsp/users/formUser").forward(request, response);
	}
	
	private void showChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id",id);
		
		ServletUtils.setResponseController(this, "/jsp/users/formPassword").forward(request, response);
	}
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		String userName=request.getParameter("user");
		String mail=request.getParameter("mail");
		String type=request.getParameter("type");
		
		User user=new User();
		user.setId(Integer.parseInt(id));
		user.setUser(userName);
		user.setMail(mail);
		user.setType(Integer.parseInt(type));		
		
		UserDAO userDAO=new UserDAOImpl();		
		if(user.getId()==0){
			user=userDAO.createUser(user);			
		}
		else{
			user=userDAO.updateUser(user);
		}		
		
		response.setContentType("text/html");
		if(user!=null){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}	
		
	}
	
	private void changeStateUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		UserDAO userDAO=new UserDAOImpl();
		
		response.setContentType("text/html");
		if(userDAO.changeStateUser(id)){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}
	}

}
