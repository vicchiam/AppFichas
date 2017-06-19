package pcs.trademark;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;

import pcs.main.Params;
import pcs.utils.FileUtils;

public class TrademarkBusiness {
	
	private TrademarkDAO trademarkDAO;
	
	public TrademarkBusiness(TrademarkDAO trademarkDAO){
		this.trademarkDAO=trademarkDAO;
	}
	
	public Collection<Trademark> listTrademarks(String name){
		return this.trademarkDAO.listTrademarks(name);
	}
	
	public Trademark getTrademark(String id){
		return this.getTrademark(Integer.parseInt(id));
	}
	
	public Trademark getTrademark(int id){
		return this.trademarkDAO.getTrademark(id);
	}
	
	public Trademark saveTrademark(String id, String name){
		return this.saveTrademark(Integer.parseInt(id), name);
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
	
	public boolean deleteTrademark(String id){
		return this.deleteTrademark(Integer.parseInt(id));
	}
	
	public boolean deleteTrademark(int id){
		return this.trademarkDAO.deleteTrademark(id);
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
	
	

}
