package pcs.weight;

import java.sql.SQLException;

import pcs.interfacesDAO.WeightDAO;

public class WeightBusiness {

	private WeightDAO weightDAO;
	
	public WeightBusiness(){
		this.weightDAO=new WeightDAOImpl();
	}
	
	public Weight getWeight(int id) throws SQLException{
		return this.weightDAO.getWeight(id);
	}
	
}
