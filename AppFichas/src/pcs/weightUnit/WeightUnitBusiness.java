package pcs.weightUnit;

import java.sql.SQLException;
import java.util.Collection;

import pcs.interfacesDAO.WeightUnitDAO;

public class WeightUnitBusiness {
	
	private WeightUnitDAO weightUnitDAO;
	
	public WeightUnitBusiness(){
		this.weightUnitDAO=new WeightUnitDAOImpl();
	} 

	public Collection<WeightUnit> listWeightUnits(int state) throws SQLException{
		return this.weightUnitDAO.listWeightUnits(state);
	}
	
	public WeightUnit getWeightUnit(int id) throws SQLException{
		return this.weightUnitDAO.getWeightUnit(id);
	}
	
	public WeightUnit getWeightUnit(String name) throws SQLException{
		return this.weightUnitDAO.getWeightUnit(name);
	}
	
	public WeightUnit saveWeightUnit(int id, String name, float conversionToKgm) throws SQLException{
		WeightUnit weightUnit=WeightUnitBuilder.weightUnit()
				.withId(id)
				.withName(name)
				.withConversionToKgm(conversionToKgm)
				.build();
		
		if(weightUnit.getId()==0){
			return this.weightUnitDAO.createWeightUnit(weightUnit);
		}
		else{
			return this.weightUnitDAO.updateWeightUnit(weightUnit);
		}
	}
	
	public boolean changeStateWeightUnit(int id) throws SQLException{
		return this.weightUnitDAO.changeStateWeightUnit(id);
	}
	
}
