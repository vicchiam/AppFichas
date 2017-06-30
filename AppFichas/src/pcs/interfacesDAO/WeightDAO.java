package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.weight.Weight;

public interface WeightDAO {

	public Weight getWeight(int id) throws SQLException;
	
	public Weight insertWeight(Weight weight) throws SQLException;
	
	public Weight updateWeight(Weight weight) throws SQLException;
	
	public boolean deleteWeight(int id) throws SQLException;
	
	public Collection<Weight> listWeightsFromPack(int idPack) throws SQLException;
	
}
