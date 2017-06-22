package pcs.weightUnit;

import java.io.IOException;
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
		else if(action.equals("showNewWeightUnit")){
			this.showNewWeightUnit(request, response);
		}
		else if(action.equals("showUpdateWeightUnit")){
			this.showUpdateWeightUnit(request, response);
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
		
		WeightUnitBusiness weightUnitBusiness=new WeightUnitBusiness(new WeightUnitDAOImpl());
		Collection<WeightUnit> list=weightUnitBusiness.listWeightUnits(Integer.parseInt(state));
		
		//list=Test.getWeigthUnits();
		
		request.setAttribute("listWeightUnits", list);		
		
		if(request.getParameter("ajax")==null){
			ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/weightUnit").forward(request, response);
		}
		else{
			ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/listWeightUnits").forward(request, response);
		}		
	}
	
	private void showNewWeightUnit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("weightUnit",new WeightUnit());		
		ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/formWeightUnit").forward(request, response);
	}
	
	private void showUpdateWeightUnit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		WeightUnitBusiness weightUnitBusiness=new WeightUnitBusiness(new WeightUnitDAOImpl());
		WeightUnit weightUnit=weightUnitBusiness.getWeightUnit(Integer.parseInt(id));
		
		request.setAttribute("weightUnit", weightUnit);
		ServletUtils.setResponseController(this, Params.JSP_PATH+"weightUnits/formWeightUnit").forward(request, response);		
	}
	
	private void saveWeightUnit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String conversion=request.getParameter("conversion");
		
		WeightUnitBusiness weightUnitBusiness=new WeightUnitBusiness(new WeightUnitDAOImpl());
		
		WeightUnit weightUnit=new WeightUnit(Integer.parseInt(id), name, Float.parseFloat(conversion));
		weightUnit=weightUnitBusiness.saveWeightUnit(weightUnit);
		if(weightUnit!=null){
			response.getWriter().print("ok");			
		}
		else{
			response.getWriter().print("Error save WeightUnit");
		}
	}
	
	private void changeStateWeightUnit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		
		WeightUnitBusiness weightUnitBusiness=new WeightUnitBusiness(new WeightUnitDAOImpl());
		
		if(weightUnitBusiness.changeStateWeightUnit(Integer.parseInt(id))){
			response.getWriter().print("ok");	
		}
		else{
			response.getWriter().print("Error change state WeightUnit");
		}
	}
	
	
	

}
