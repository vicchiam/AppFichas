package pcs.weightUnit;

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
 * Servlet implementation class WeightUnitController
 */
public class WeightUnitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeightUnitController() {
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
			this.showListWeightUnits(request, response);			
		}		
		else if(action.equals("showFormWeightUnit")){
			this.showFormWeightUnit(request, response);
		}
		else if(action.equals("saveWeightUnit")){
			this.saveWeightUnit(request, response);
		}		
		else if(action.equals("changeStateWeightUnit")){
			this.changeStateWeightUnit(request, response);
		}
		
	}
	
	private void showListWeightUnits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("WUNT",300,160,"Conversion de unidades de peso"));				
		request.setAttribute("windows", windows);
		
		String state=request.getParameter("f_state");
		if(state==null) state="1";
		request.setAttribute("f_state", state);
		
		try{
			Collection<WeightUnit> list=new WeightUnitBusiness().listWeightUnits(Integer.parseInt(state));
			
			request.setAttribute("listWeightUnits", list);		
			
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/weightUnit").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/listWeightUnits").forward(request, response);
			}	
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			ServletUtils.showError(this, request, response, e.getMessage());
		}
		
	}
	
	private void showFormWeightUnit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		WeightUnit weightUnit=WeightUnitBuilder.weightUnit().build();
		
		try {
			if(!id.equals(Params.EMPTY_ID+"")){
				weightUnit = new WeightUnitBusiness().getWeightUnit(Integer.parseInt(id));
			}
			request.setAttribute("weightUnit", weightUnit);
			ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/formWeightUnit").forward(request, response);
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			ServletUtils.showError(this, request, response, e.getMessage());
		}				
	}
	
	private void saveWeightUnit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String conversion=request.getParameter("conversion");
		
		try{
			WeightUnit weightUnit=new WeightUnitBusiness().saveWeightUnit(Integer.parseInt(id), name, Float.parseFloat(conversion));
			if(weightUnit!=null){
				response.getWriter().print("ok");			
			}
			else{
				response.getWriter().print("Error save WeightUnit");
			}
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}
	
	private void changeStateWeightUnit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		
		try{
			if(new WeightUnitBusiness().changeStateWeightUnit(Integer.parseInt(id))){
				response.getWriter().print("ok");	
			}
			else{
				response.getWriter().print("Error change state WeightUnit");
			}
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}	

}
