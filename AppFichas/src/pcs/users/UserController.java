package pcs.users;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import pcs.utils.InitParams;
import pcs.utils.ServletUtils;

/**
 * Servlet implementation class UsersServlet
 */
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger("UserController: ");
       
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
		
		String pathJSP=config.getInitParameter("pathJSP");
		String pathCSS=config.getInitParameter("pathCSS");
		String pathJS=config.getInitParameter("pathJS");		
		InitParams.getInstance(pathJSP,pathCSS,pathJS);
		
		BasicConfigurator.configure();
				
	}    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String action=request.getParameter("action");
		
		if(action.equals("startSession")){			
			
			String userName=request.getParameter("user");
			String password=request.getParameter("password");
			
			UserDAO userDAO = new UserDAOImpl();
			User user=userDAO.loginUser(userName, password);
			
			if(user.getId()!=0){
				ServletUtils.setResponseController(this, "index").forward(request, response);
			}
			else{
				request.setAttribute("error", "user/pass incorrect");
				ServletUtils.setResponseController(this, "user/login").forward(request, response);
			}
		}
		*/
		
	}

}
