package pcs.weight;

import java.sql.SQLException;
import java.util.Collection;

import pcs.interfacesDAO.WeightDAO;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitBuilder;

public class WeightBusiness {

	private WeightDAO weightDAO;
	
	public WeightBusiness(){
		this.weightDAO=new WeightDAOImpl();
	}
	
	public Weight getWeight(int id) throws SQLException{
		return this.weightDAO.getWeight(id);
	}
	
	public Collection<Weight> listWeightsFromPack(int idPack) throws SQLException{
		return this.weightDAO.listWeightsFromPack(idPack);
	}
	
	public Weight insertWeight(float value, int idWeightUnit) throws SQLException{
		WeightUnit weightUnit=WeightUnitBuilder
				.weightUnit()
				.withId(idWeightUnit)
				.build();
		
		Weight weight=WeightBuilder
				.weight()
				.withValue(value)
				.withWeightUnit(weightUnit)
				.build();
				
		return this.weightDAO.insertWeight(weight);
	}
	
	public Weight updateWeight(int id, float value, int idWeightUnit) throws SQLException{
		WeightUnit weightUnit=WeightUnitBuilder
				.weightUnit()
				.withId(idWeightUnit)
				.build();
		
		Weight weight=WeightBuilder
				.weight()
				.withId(id)
				.withValue(value)
				.withWeightUnit(weightUnit)
				.build();
		return this.weightDAO.updateWeight(weight);
	}
	
	public boolean deleteWeight(int id) throws SQLException{
		return this.weightDAO.deleteWeight(id);
	}
	
}
