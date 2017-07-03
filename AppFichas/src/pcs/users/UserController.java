package pcs.users;

import java.io.IOException;
import java.sql.SQLException;
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
				
		if(action.equals("list")){
			this.showListUsers(request, response);	
		}		
		else if(action.equals("showFormUser")){
			this.showFormUser(request, response);
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
		if(state==null) state=Params.ACTIVE+"";
		request.setAttribute("f_state", state);		
		
		try{
			Collection<User> listUsers=new UserBusiness().listUsers(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
			
			request.setAttribute("listUsers", listUsers);
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"users/user").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"users/listUsers").forward(request, response);
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
	
	private void showFormUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		User user=UserBuilder.user().build();
		
		try {
			if(!id.equals(Params.EMPTY_ID+"")){
				user = new UserBusiness().getUser(Integer.parseInt(id));
			}
			request.setAttribute("user",user);
			ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formUser").forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showError(this, request, response, e.getMessage());
		}
		
	}
	
	private void showChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id",id);
		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formPassword").forward(request, response);
	}
	
	private void showUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id",id);
		
		try{
			TrademarkBusiness trademarkBusiness=new TrademarkBusiness();
			Collection<Trademark> trademarks=trademarkBusiness.listUserTrademarksNot(Integer.parseInt(id));
			Collection<Trademark> userTrademarks=trademarkBusiness.listUserTrademarks(Integer.parseInt(id));
			
			request.setAttribute("trademarks", trademarks);
			request.setAttribute("userTrademarks", userTrademarks);		
			
			ServletUtils.setResponseController(this, Params.JSP_PATH+"users/formUserTrademark").forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showError(this, request, response, e.getMessage());
		}
		
	}
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		String userName=request.getParameter("user");
		String mail=request.getParameter("mail");
		String type=request.getParameter("type");
		
		try{
			User user=new UserBusiness().saveUser(Integer.parseInt(id), userName, mail, Integer.parseInt(type));
			
			response.setContentType("text/html");
			if(user!=null){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}		
		
	}
	
	private void changeStateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		
		try{		
			response.setContentType("text/html");
			if(new UserBusiness().changeStateUser(Integer.parseInt(id))){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}

	private void savePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		try{		
			response.setContentType("text/html");
			if(new UserBusiness().savePassword(Integer.parseInt(id), password)){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}
	
	private void autocompleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String mail=request.getParameter("f_mail");
		request.setAttribute("f_mail", mail);
		String type=request.getParameter("f_type");
		request.setAttribute("f_type", type);		
		String state=request.getParameter("f_state");		
		request.setAttribute("f_state", state);				
		
		try{
			String json=new UserBusiness().autocompleteUser(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
			ServletUtils.responseJSON(response, json);	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}
	
	private void autocompleteMail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String userName=request.getParameter("f_user");
		request.setAttribute("f_user", userName);
		String mail=request.getParameter("f_mail");
		request.setAttribute("f_mail", mail);
		String type=request.getParameter("f_type");
		request.setAttribute("f_type", type);		
		String state=request.getParameter("f_state");		
		request.setAttribute("f_state", state);				
		
		try{
			String json=new UserBusiness().autocompleteMail(userName, mail, Integer.parseInt(type), Integer.parseInt(state));			
			ServletUtils.responseJSON(response, json);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
		
	}
	
	private void addUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String idUser=request.getParameter("id");
		String idsTrademarks=request.getParameter("idsTrademarks");
				
		try{
			TrademarkBusiness trademarkBusiness=new TrademarkBusiness();
			for(String idTrademark : idsTrademarks.split("__")){
				if(!trademarkBusiness.addUserTrademark(Integer.parseInt(idUser), Integer.parseInt(idTrademark))){
					response.getWriter().print("Error add User-Trademark");	
				}
			}
			response.getWriter().print("ok");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
		
	}
	
	private void removeUserTrademarks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String idUser=request.getParameter("id");
		String idsTrademarks=request.getParameter("idsTrademarks");
		
		try{
			TrademarkBusiness trademarkBusiness=new TrademarkBusiness();			
			for(String idTrademark : idsTrademarks.split("__")){
				if(!trademarkBusiness.removeUserTrademark(Integer.parseInt(idUser), Integer.parseInt(idTrademark))){
					response.getWriter().print("Error remove User-Trademark");	
				}
			}
			response.getWriter().print("ok");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}	
	
}
