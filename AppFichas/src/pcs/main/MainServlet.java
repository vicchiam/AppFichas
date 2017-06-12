package pcs.main;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcs.users.User;
import pcs.users.UserDAO;
import pcs.users.UserDAOImpl;
import pcs.utils.ServletUtils;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pathJSP;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		pathJSP=config.getInitParameter("pathJSP");				
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action");
		
		if(action==null){
			ServletUtils.setResponseController(this, "/jsp/login").forward(request, response);
		}
		else if(action.equals("logout")){
			HttpSession session=request.getSession();
			session.invalidate();
			ServletUtils.setResponseController(this, "/jsp/login").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		
		if(action.equals("startSession")){			
			
			String userName=request.getParameter("user");
			String password=request.getParameter("password");
			
			UserDAO userDAO = new UserDAOImpl();
			User user=userDAO.loginUser(userName, password);
			
			if(user.getId()!=0){
				HttpSession session=request.getSession();
				session.setAttribute("user", user);
				ServletUtils.setResponseController(this, "/jsp/index").forward(request, response);
			}
			else{
				request.setAttribute("error", "user/pass incorrect");
				ServletUtils.setResponseController(this, "/jsp/login").forward(request, response);
			}
		}
		
	}

}
