package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.weightUnit.WeightUnit;

public interface WeightUnitDAO {
	
	public Collection<WeightUnit> listWeightUnits(int state) throws SQLException;
	
	public WeightUnit getWeightUnit(int id) throws SQLException;
	
	public WeightUnit getWeightUnit(String name) throws SQLException;
	
	public WeightUnit createWeightUnit(WeightUnit weightUnit) throws SQLException;
	
	public WeightUnit updateWeightUnit(WeightUnit weightUnit) throws SQLException;
	
	public boolean changeStateWeightUnit(int id) throws SQLException;

}
