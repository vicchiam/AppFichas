package pcs.weightUnit;

import java.util.Collection;

public interface WeightUnitDAO {
	
	public Collection<WeightUnit> listWeightUnits(int state);
	
	public WeightUnit getWeightUnit(int id);
	
	public WeightUnit getWeightUnit(String name);
	
	public WeightUnit createWeightUnit(WeightUnit weightUnit);
	
	public WeightUnit updateWeightUnit(WeightUnit weightUnit);
	
	public boolean changeStateWeightUnit(int id);

}
