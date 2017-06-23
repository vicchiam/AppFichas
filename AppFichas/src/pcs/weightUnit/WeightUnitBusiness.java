package pcs.weightUnit;

import java.util.Collection;

import pcs.interfacesDAO.WeightUnitDAO;

public class WeightUnitBusiness {
	
	private WeightUnitDAO weightUnitDAO;
	
	public WeightUnitBusiness(WeightUnitDAO weightUnitDAO){
		this.weightUnitDAO=weightUnitDAO;
	} 

	public Collection<WeightUnit> listWeightUnits(int state){
		return this.weightUnitDAO.listWeightUnits(state);
	}
	
	public WeightUnit getWeightUnit(int id){
		return this.weightUnitDAO.getWeightUnit(id);
	}
	
	public WeightUnit getWeightUnit(String name){
		return this.weightUnitDAO.getWeightUnit(name);
	}
	
	public WeightUnit saveWeightUnit(WeightUnit weightUnit){
		if(weightUnit.getId()==0){
			return this.weightUnitDAO.createWeightUnit(weightUnit);
		}
		else{
			return this.weightUnitDAO.updateWeightUnit(weightUnit);
		}
	}
	
	public boolean changeStateWeightUnit(int id){
		return this.weightUnitDAO.changeStateWeightUnit(id);
	}
	
}
