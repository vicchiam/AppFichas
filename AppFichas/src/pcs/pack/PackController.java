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

import pcs.utils.Params;
import pcs.utils.ServletUtils;
import pcs.utils.Window;
import pcs.weight.Weight;
import pcs.weight.WeightBuilder;
import pcs.weight.WeightBusiness;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitBusiness;

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
		
		request.setAttribute("aptNames",Pack.aptNames);		
		
		if(action.equals("list")){
			this.showListPacks(request, response);
		}
		else if(action.equals("showNewPack")){
			this.showNewPack(request, response);
		}
		else if(action.equals("showUpdatePack")){
			this.showUpdatePack(request, response);
		}
		else if(action.equals("savePack")){
			this.savePack(request,response);
		}
		else if(action.equals("changeStatePack")){
			this.chageStatePack(request, response);
		}
		else if(action.equals("autocompleteDescription")){
			this.autocompleteDescription(request,response);
		}
		else if(action.equals("showListPackWeight")){
			this.showListPackWeight(request, response);
		}
		else if(action.equals("showFormPackWeight")){
			this.showFormPackWeight(request, response);
		}
	}
	
	private void showListPacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("PACK",400,220,"Envases"));	
		windows.add(new Window("PCKW",600,450,"Pesos vacios"));
		windows.add(new Window("PCKWF",300,200,"Pesos vacio"));
		request.setAttribute("windows", windows);
				
		String description=request.getParameter("f_description");
		request.setAttribute("f_description", description);
		
		String apt=request.getParameter("f_apt");
		if(apt==null) apt=Pack.APT_EMPTY+"";
		request.setAttribute("f_apt", apt);		
		
		String state=request.getParameter("f_state");
		if(state==null) state=Params.ACTIVE+"";
		request.setAttribute("f_state", state);		
		
		try{
			Collection<Pack> listPacks=new PackBusiness().listPacks(description,Integer.parseInt(apt), Integer.parseInt(state));
			request.setAttribute("listPacks", listPacks);			
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/pack").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/listPacks").forward(request, response);
			}			
						
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			if(request.getParameter("ajax")==null){
				ServletUtils.showError(this,request, response, e.getMessage());
			}
			else{
				ServletUtils.showErrorAjax(request, response, e.getMessage());
			}
		}		
	}
	
	private void showNewPack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("pack", PackBuilder.pack().build());				
		ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/formPack").forward(request, response);
	}
	
	private void showUpdatePack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		try {
			Pack pack=new PackBusiness().getPack(Integer.parseInt(id));
			request.setAttribute("pack", pack);
			ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/formPack").forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showError(this,request, response, "Id pack error");
		}		
		
	}
	
	private void savePack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		String description=request.getParameter("description");
		String mesure=request.getParameter("mesure");		
		String apt=request.getParameter("apt");
		
		int aptValue=(apt.equals("on")?Pack.APT:Pack.NO_APT);
		
		try {
			Pack pack=new PackBusiness().savePack(Integer.parseInt(id), description, mesure, aptValue);
			if(pack!=null){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, "Error save pack");
		}
	}
	
	private void chageStatePack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		
		try {
			if(new PackBusiness().changeStatePack(Integer.parseInt(id))){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, "Error change state pack");
		}
	}
	
	private void autocompleteDescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String description=request.getParameter("f_description");
		request.setAttribute("f_description", description);
		
		String apt=request.getParameter("f_apt");
		if(apt==null) apt="0";
		request.setAttribute("f_apt", apt);		
		
		String state=request.getParameter("f_state");
		if(state==null) state="1";
		request.setAttribute("f_state", state);
		
		try{
			String json=new PackBusiness().autocompleteDescription(description, Integer.parseInt(apt), Integer.parseInt(state));			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}		
	}
	
	private void showListPackWeight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/listPackWeight").forward(request, response);				
	}
	
	private void showFormPackWeight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		Weight weight=WeightBuilder.weight().build();
		
		try{
			Collection<WeightUnit> weightUnits=new WeightUnitBusiness().listWeightUnits(Params.ACTIVE);
			request.setAttribute("weightUnits", weightUnits);		
			
			if(id!=null){				
				weight=new WeightBusiness().getWeight(Integer.parseInt(id));			
			}
			
			request.setAttribute("weight", weight);
			ServletUtils.setResponseController(this, Params.JSP_PATH+"packs/formPackWeight").forward(request, response);
		} catch (NumberFormatException | SQLException e) {				
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
	}
		

}
