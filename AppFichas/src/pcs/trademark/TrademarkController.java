package pcs.trademark;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import pcs.utils.Params;
import pcs.utils.ServletUtils;
import pcs.utils.Window;

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
		else if(action.equals("showFormTrademark")){
			this.showFormTrademark(request, response);
		}
		else if(action.equals("saveTrademark")){
			this.saveTrademark(request, response);			
		}
		else if(action.equals("changeStateTrademark")){
			this.changeStateTrademark(request, response);			
		}
		else if(action.equals("showImageUpdater")){
			this.showImageUpdater(request, response);
		}
		else if(action.equals("upload")){
			this.updateFile(request, response);			
		}
		else if(action.equals("autocompleteName")){
			this.autocompleteName(request,response);
		}
		else if(action.equals("deleteImage")){
			this.deleteImage(request,response);
		}		
	}
	
	private void showListTrademarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Collection<Window> windows=new ArrayList<Window>();
		windows.add(new Window("TMK",300,120,"Marca"));			
		windows.add(new Window("IMG",350,350,"Imagen de la marca"));		
		request.setAttribute("windows", windows);		
		
		String name=request.getParameter("f_name");
		request.setAttribute("f_name", name);
		
		String state=request.getParameter("f_state");
		if(state==null) state=Params.ACTIVE+"";
		request.setAttribute("f_state", state);		
		
		try {
			Collection<Trademark> list=new TrademarkBusiness().listTrademarks(name,Integer.parseInt(state));
			request.setAttribute("listTrademarks", list);		
			if(request.getParameter("ajax")==null){
				ServletUtils.setResponseController(this, Params.JSP_PATH+"trademarks/trademark").forward(request, response);
			}
			else{
				ServletUtils.setResponseController(this, Params.JSP_PATH+"trademarks/listTrademarks").forward(request, response);
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
	
	private void showFormTrademark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		Trademark trademark=TrademarkBuilder.trademark().build();
		
		try {
			if(id!=null && !id.equals("0")){
				trademark = new TrademarkBusiness().getTrademark(Integer.parseInt(id));
			}
			request.setAttribute("trademark",trademark);		
			ServletUtils.setResponseController(this, Params.JSP_PATH+"trademarks/formTrademark").forward(request, response);			
		}
		catch (NumberFormatException | SQLException e) {		
			e.printStackTrace();
			ServletUtils.showError(this,request, response, e.getMessage());
		}							
		
	}
	
	private void saveTrademark(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		
		try {
			Trademark trademark=new TrademarkBusiness().saveTrademark(Integer.parseInt(id), name);
			response.setContentType("text/html");
			if(trademark!=null){
				response.getWriter().print("ok");
			}
			else{
				response.getWriter().print("error");
			}
		}
		catch (NumberFormatException | SQLException e) {	
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}		
		
	}
	
	private void changeStateTrademark(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id=request.getParameter("id");		
		
		try {			
			response.setContentType("text/html");
			if(new TrademarkBusiness().changeStateTrademark(Integer.parseInt(id))){
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
	
	private void showImageUpdater(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("action","/AppFichas/Trademarks?action=upload");
				
		String id=request.getParameter("id");
		request.setAttribute("id", id);
		String path=request.getParameter("path");
		request.setAttribute("path", path);
						
		ServletUtils.setResponseController(this, Params.JSP_PATH+"image/formImageUpload").forward(request, response);		
	}
	
	private void autocompleteName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("f_name");
		request.setAttribute("f_name", name);
		
		String state=request.getParameter("f_state");
		if(state==null) state="1";
		request.setAttribute("f_state", state);	
		
		try {
			String json;
			json = new TrademarkBusiness().autocompleteName(name, Integer.parseInt(state));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}		
		
	}
	
	private void updateFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String DIRECTORY = request.getServletContext().getRealPath("/");		
		System.out.println(DIRECTORY);		
						
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
				
				String resp=new TrademarkBusiness().uploadFile(multiparts, DIRECTORY);
				
				PrintWriter out=response.getWriter();
				out.println(this.doUploadMessage(resp));				
			} catch (FileUploadException e) {				
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String DIRECTORY = request.getServletContext().getRealPath("/");
		String id=request.getParameter("id");		
		
		try {
			if(new TrademarkBusiness().deleteFile(DIRECTORY, Integer.parseInt(id))){
				response.getWriter().print("ok");	
			}
			else{
				response.getWriter().print("Error delete file");	
			}
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			ServletUtils.showErrorAjax(request, response, e.getMessage());
		}
		
	}
	
	private String doUploadMessage(String message){
		String html="";
		html+="<html><head><title></title><body>";
		html+="<script type='text/javascript'>";
		html+="parent.CallbackImageUpdater('"+message+"')";
		html+="</script></body></html>";
		return html;		
	} 	
	
	

}
