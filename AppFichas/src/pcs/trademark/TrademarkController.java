package pcs.trademark;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import pcs.main.Window;
import pcs.main.Params;
import pcs.test.Test;
import pcs.users.User;
import pcs.utils.FileUtils;
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
		else if(action.equals("upload")){
			this.updateFile(request, response);			
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
		request.setAttribute("action","/AppFichas/Trademarks?action=upload");
				
		String id=request.getParameter("id");
		request.setAttribute("id", id);
		String path=request.getParameter("path");
		request.setAttribute("path", path);
						
		ServletUtils.setResponseController(this, "/jsp/image/formImageUpload").forward(request, response);		
	}
	
	private void updateFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String DIRECTORY = request.getServletContext().getRealPath("/");		
		System.out.println(DIRECTORY);
		
		String id="";
						
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				String html="";
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
				for(FileItem item: multiparts){
					if (item.isFormField()) {
						String name = item.getFieldName();
						if(name.equals("_id")){
							id=item.getString();							
						}					    					    
				    } else {
				    	String realName = new File(item.getName()).getName();				    					    	
				    	String extension=FileUtils.getFileExtension(realName);
				    	if(extension!=null){
				    		String name=id+"_trademark"+extension;
				    		item.write( new File(DIRECTORY+Params.UPLOAD_DIRECTORY + File.separator + name));	
				    		html=this.doUploadMessage("ok");
				    	}
				    	else{
				    		html=this.doUploadMessage("File Name Error");
				    	}
				    }
				}
				PrintWriter out=response.getWriter();
				out.println(html);				
			} catch (FileUploadException e) {				
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
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
