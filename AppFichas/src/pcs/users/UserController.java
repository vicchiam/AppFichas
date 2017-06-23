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

import pcs.trademark.Trademark;
import pcs.trademark.TrademarkBusiness;
import pcs.trademark.TrademarkDAOImpl;
import pcs.utils.Params;
import pcs.utils.ServletUtils;
import pcs.utils.Window;

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
			ServletUtils.setResponseController(this,Params.JSP_PATH+"login").forward(request, response);
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
		else if(action.equals("showChangePassword")){
			this.showChangePassword(request, response);	
		}
		else if(action.equals("showUserTrademarks")){
			this.showUserTrademarks(request, response);	
		}
		else if(action.equals("saveUser")){
			this.saveUser(request, response);	
		}
		else if(action.equals("changeStateUser")){
			this.changeStateUser(request, response);	
		}		
		else if(action.equals("savePassword")){
			this.savePassword(request, response);	
		}
		else if(action.equals("autocompleteUser")){
			this.autocompleteUser(request, response);
		}
		else if(action.equals("autocompleteMail")){
			this.autocompleteMail(request, response);
		}
		else if(action.equals("addUserTrademarks")){
			this.addUserTrademarks(request, response);
		}
		else if(action.equals("removeUserTrademarks")){
			this.removeUserTrademarks(request, response);
		}
		else{
			ServletUtils.setResponseController(this, Params.JSP_PATH+"index").forward(request, response);
		}	
		
	}
	
	private void showListUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("USR",400,200,"Usuario"));
		windows.add(new Window("USR_TMK",800,600,"Marcas de usuario"));
		request.setAttribute("windows", windows);
		
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String mail=request.getParameter("f_mail");
		request.setAttribute("f_mail", mail);
		String type=request.getParameter("f_type");
		if(type==null) type="0";
		request.setAttribute("f_type", type);		
		String state=request.getParameter("f_state");
		if(state==null) state="1";
		request.setAttribute("f_state", state);		
		
		UserBusiness userBusiness=new UserBusiness();
		Collection<User> listUsers=userBusiness.listUsers(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
		
		request.setAttribute("listUsers", listUsers);
		if(request.getParameter("ajax")==null){
			ServletUtils.setResponseController(this, Params.JSP_PATH+"users/user").forward(request, response);
		}
		else{
			ServletUtils.setResponseController(this, Params.JSP_PATH+"users/listUsers").forward(request, response);
		}		
	}
	
	private void showNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("user",new User());		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formUser").forward(request, response);
	}
	
	private void showUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		UserBusiness userBusiness=new UserBusiness();
		User user=userBusiness.getUser(Integer.parseInt(id));
		request.setAttribute("user",user);
		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formUser").forward(request, response);
	}
	
	private void showChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id",id);
		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formPassword").forward(request, response);
	}
	
	private void showUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id",id);
		
		TrademarkBusiness trademarkBusiness=new TrademarkBusiness();
		Collection<Trademark> trademarks=trademarkBusiness.listUserTrademarksNot(Integer.parseInt(id));
		Collection<Trademark> userTrademarks=trademarkBusiness.listUserTrademarks(Integer.parseInt(id));
		
		request.setAttribute("trademarks", trademarks);
		request.setAttribute("userTrademarks", userTrademarks);		
		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formUserTrademark").forward(request, response);
	}
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		String userName=request.getParameter("user");
		String mail=request.getParameter("mail");
		String type=request.getParameter("type");
		
		UserBusiness userBusiness=new UserBusiness();	
		User user=userBusiness.saveUser(Integer.parseInt(id), userName, mail, Integer.parseInt(type));
		
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
		
		UserBusiness userBusiness=new UserBusiness();
		
		response.setContentType("text/html");
		if(userBusiness.changeStateUser(Integer.parseInt(id))){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}
	}

	private void savePassword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		UserBusiness userBusiness=new UserBusiness();
		
		response.setContentType("text/html");
		if(userBusiness.savePassword(Integer.parseInt(id), password)){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}		
	}
	
	private void autocompleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String mail=request.getParameter("f_mail");
		request.setAttribute("f_mail", mail);
		String type=request.getParameter("f_type");
		request.setAttribute("f_type", type);		
		String state=request.getParameter("f_state");		
		request.setAttribute("f_state", state);				
		
		String json=new UserBusiness().autocompleteUser(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);		
	}
	
	private void autocompleteMail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String mail=request.getParameter("f_mail");
		request.setAttribute("f_mail", mail);
		String type=request.getParameter("f_type");
		request.setAttribute("f_type", type);		
		String state=request.getParameter("f_state");		
		request.setAttribute("f_state", state);				
		
		String json=new UserBusiness().autocompleteMail(userName, mail, Integer.parseInt(type), Integer.parseInt(state));		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);		
	}
	
	private void addUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String idUser=request.getParameter("id");
		String idsTrademarks=request.getParameter("idsTrademarks");
		
		TrademarkBusiness trademarkBusiness=new TrademarkBusiness();
		
		for(String idTrademark : idsTrademarks.split("__")){
			if(!trademarkBusiness.addUserTrademark(Integer.parseInt(idUser), Integer.parseInt(idTrademark))){
				response.getWriter().print("Error add User-Trademark");	
			}
		}
		response.getWriter().print("ok");		
	}
	
	private void removeUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String idUser=request.getParameter("id");
		String idsTrademarks=request.getParameter("idsTrademarks");
		
		TrademarkBusiness trademarkBusiness=new TrademarkBusiness();
		
		for(String idTrademark : idsTrademarks.split("__")){
			if(!trademarkBusiness.removeUserTrademark(Integer.parseInt(idUser), Integer.parseInt(idTrademark))){
				response.getWriter().print("Error remove User-Trademark");	
			}
		}
		response.getWriter().print("ok");	
	}
	
}
