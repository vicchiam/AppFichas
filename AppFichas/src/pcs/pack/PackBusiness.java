package pcs.pack;

import java.sql.SQLException;
import java.util.Collection;

import pcs.interfacesDAO.PackDAO;

public class PackBusiness {

	private PackDAO packDAO;
	
	public PackBusiness(){
		this.packDAO=new PackDAOImpl();
	}
	
	public Collection<Pack> listPacks(String description , int apt, int state) throws SQLException {
		return this.packDAO.listPacks(description, apt, state);
	}
	
	public Pack getPack(int id) throws SQLException{
		return this.packDAO.getPack(id);
	}
	
	public Pack savePack(int id, String description, String mesure, String weight, int apt) throws SQLException{
		Pack pack=new Pack(id,description, mesure, weight, apt,1);
		if(pack.getId()==0){
			return this.packDAO.insertPack(pack);
		}
		else{
			return this.packDAO.updatePack(pack);
		}
	}
	
	public boolean changeStatePack(int id) throws SQLException{
		return this.packDAO.changeStatePack(id);
	}	
	
}
