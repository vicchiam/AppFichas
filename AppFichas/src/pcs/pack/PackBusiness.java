package pcs.pack;

import java.sql.SQLException;
import java.util.Collection;

import org.json.simple.JSONArray;

import pcs.interfacesDAO.PackDAO;
import pcs.utils.Params;

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
	
	public Pack savePack(int id, String description, String mesure, int apt) throws SQLException{
		Pack pack=PackBuilder.pack()
				.withId(id)
				.withDescription(description)
				.withMesure(mesure)
				.withApt(apt)
				.build();
		if(pack.getId()==Params.EMPTY_ID){
			return this.packDAO.insertPack(pack);
		}
		else{
			return this.packDAO.updatePack(pack);
		}
	}
	
	public boolean changeStatePack(int id) throws SQLException{
		return this.packDAO.changeStatePack(id);
	}	
	
	@SuppressWarnings("unchecked")
	public String autocompleteDescription(String description , int apt, int state) throws SQLException {
		JSONArray root = new JSONArray();
		Collection<Pack> listPacks=this.packDAO.listPacks(description, apt, state);
		for(Pack pack : listPacks){
			root.add(pack.getDescription());
		}
		return root.toJSONString();	
	}
	
	public boolean savePackWeight(int idPack, int idWeight, float value, int idWeightUnit){
		
		return false;
	}
	
}
