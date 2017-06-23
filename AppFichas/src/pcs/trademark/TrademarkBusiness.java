package pcs.trademark;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.json.simple.JSONArray;

import pcs.interfacesDAO.TrademarkDAO;
import pcs.users.User;
import pcs.utils.FileUtils;
import pcs.utils.Params;

public class TrademarkBusiness {
	
	private TrademarkDAO trademarkDAO;
	
	public TrademarkBusiness(){
		this.trademarkDAO=new TrademarkDAOImpl();
	}
	
	public Collection<Trademark> listTrademarks(){
		return this.trademarkDAO.listTrademarks("",1);
	}
	
	public Collection<Trademark> listTrademarks(String name, int state){
		return this.trademarkDAO.listTrademarks(name, state);
	}
	
	public Trademark getTrademark(int id){
		return this.trademarkDAO.getTrademark(id);
	}
	
	public Trademark saveTrademark(int id, String name){
		Trademark trademark=new Trademark(id,name);
		
		if(trademark.getId()==0){
			trademark=trademarkDAO.createTrademark(trademark);
		}
		else{
			trademark=trademarkDAO.updateTrademark(trademark);
		}
		
		return trademark;		
	}
	
	public boolean changeStateTrademark(int id){
		return this.trademarkDAO.changeStateTrademark(id);
	}
	
	public Collection<Trademark> listUserTrademarks(int idUser){
		return this.trademarkDAO.listTrademarksForUser(idUser);
	}
	
	public Collection<Trademark> listUserTrademarksNot(int idUser){
		return this.trademarkDAO.listTrademarksForUserNot(idUser);
	}
	
	public boolean addUserTrademark(int idUser, int idTrademark){
		return this.trademarkDAO.addUserTrademark(idUser, idTrademark);
	}
	
	public boolean removeUserTrademark(int idUser, int idTrademark){
		return this.trademarkDAO.removeUserTrademark(idUser, idTrademark);
	}
	
	@SuppressWarnings("unchecked")
	public String autocompleteName(String name, int state){
		JSONArray root = new JSONArray();
		
		Collection<Trademark> listTrademarks=this.trademarkDAO.listTrademarks(name, state);
		for(Trademark trademark : listTrademarks){
			root.add(trademark.getName());
		}		
		return root.toJSONString();		
	}
	
	public String uploadFile(List<FileItem> multiparts, String DIRECTORY) throws Exception{
		String id="";
		FileItem file=null;
		for(FileItem item: multiparts){
			if (item.isFormField()) {
				String name = item.getFieldName();
				if(name.equals("id") || name.equals("_id") ){
					id=item.getString();							
				}					    					    
		    }
			else{
		    	file=item;
		    }
		}
		
		String realName = new File(file.getName()).getName();				    					    	
    	String extension=FileUtils.getFileExtension(realName);
    	if(extension!=null){
    		String name=id+"_trademark"+extension;
    		String realPath=DIRECTORY+Params.UPLOAD_DIRECTORY + File.separator + name;
    		String relativePath=Params.UPLOAD_DIRECTORY + File.separator + name;
    		relativePath=relativePath.replace("\\", "");
    		
    		TrademarkDAO trademarkDAO=new TrademarkDAOImpl();
    		if(trademarkDAO.updateTrademarkPath(Integer.parseInt(id), relativePath)){
    			file.write(new File(realPath));	
    			return "ok";
    		}
    		else{
    			return "SQL Error";
    		}
    	}
    	else{
    		return "File Name Error";
    	}
	}	
	
	public boolean deleteFile(String DIRECTORY, int id){
		String path=this.getTrademark(id).getPath();
		String realPath=DIRECTORY+path;
		File f=new File(realPath);
		if(f.delete()){
			return trademarkDAO.updateTrademarkPath(id, "");			
		}
		return false;
	}

}
