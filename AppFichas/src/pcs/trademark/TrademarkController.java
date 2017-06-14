package pcs.trademark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcs.main.Window;
import pcs.test.Test;
import pcs.users.User;
import pcs.utils.ServletUtils;

/**
 * Servlet implementation class TrademarkController
 */
public class TrademarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrademarkController() {
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
			this.showListTrademarks(request, response);
		}
		else if(action.equals("showNewTrademark")){
			this.showNewTrademark(request, response);
		}
		else if(action.equals("showUpdateTrademark")){
			this.showUpdateTrademark(request, response);
		}
		else if(action.equals("saveTrademark")){
			this.saveTrademark(request, response);			
		}
		else if(action.equals("deleteTrademark")){
			this.deleteTrademark(request, response);			
		}
		else if(action.equals("showImageUpdater")){
			this.showImageUpdater(request, response);
		}
		
	}
	
	private void showListTrademarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("TMK",300,120,"Marca"));			
		windows.add(new Window("IMG",350,350,"Imagen de la marca"));		
		request.setAttribute("windows", windows);		
		
		String name=request.getParameter("f_name");
		request.setAttribute("f_name", name);
		
		TrademarkDAO trademarkDAO=new TrademarkDAOImpl();
		Collection<Trademark> list=trademarkDAO.listTrademarks(name);		
		request.setAttribute("listTrademarks", list);
		
		if(request.getParameter("ajax")==null){
			ServletUtils.setResponseController(this, "/jsp/trademarks/trademark").forward(request, response);
		}
		else{
			ServletUtils.setResponseController(this, "/jsp/trademarks/listTrademarks").forward(request, response);
		}		
	}
	
	private void showNewTrademark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("trademark",new Trademark());		
		ServletUtils.setResponseController(this, "/jsp/trademarks/formTrademark").forward(request, response);
	}
	
	private void showUpdateTrademark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		TrademarkDAO trademarkDAO=new TrademarkDAOImpl();
		Trademark trademark=trademarkDAO.getTrademark(id);		
				
		request.setAttribute("trademark",trademark);		
		ServletUtils.setResponseController(this, "/jsp/trademarks/formTrademark").forward(request, response);
	}
	
	private void saveTrademark(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		
		Trademark trademark=new Trademark(Integer.parseInt(id), name);
		
		TrademarkDAO trademarkDAO=new TrademarkDAOImpl();
		
		if(trademark.getId()==0){
			trademark=trademarkDAO.createTrademark(trademark);
		}
		else{
			trademark=trademarkDAO.updateTrademark(trademark);
		}
		
		response.setContentType("text/html");
		if(trademark!=null){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}
	}
	
	private void deleteTrademark(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		
		TrademarkDAO trademarkDAO=new TrademarkDAOImpl();
		response.setContentType("text/html");
		if(trademarkDAO.deleteTrademark(id)){
			response.getWriter().print("ok");
		}
		else{
			response.getWriter().print("error");
		}
	}
	
	private void showImageUpdater(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("id", id);
		
		ServletUtils.setResponseController(this, "/jsp/image/formImageUpload").forward(request, response);		
	}

}
