package pcs.interfacesDAO;

import java.sql.SQLException;

import pcs.weight.Weight;

public interface WeightDAO {

	public Weight getWeight(int id) throws SQLException;
	
	public Weight createWeight(Weight weight) throws SQLException;
	
	public Weight updateWeight(Weight weight) throws SQLException;
	
	public boolean deleteWeight(int id) throws SQLException;
	
}
